import os
import numpy as np
import pandas as pd

BASE_DIR = os.path.dirname(os.path.abspath(__file__))


def path(filename):
    return os.path.join(BASE_DIR, filename)


def section(title):
    print("\n" + "=" * 70)
    print(title)
    print("=" * 70)


def strip_object_columns(df):
    for col in df.columns:
        if df[col].dtype == object or pd.api.types.is_string_dtype(df[col]):
            df[col] = df[col].astype(str).str.strip()
    return df


def read_data():
    section("PART 1 - READ DATA")
    try:
        funds = pd.read_csv(path("funds.csv"))
        investors = pd.read_csv(path("investors.csv"))
        transactions = pd.read_csv(path("transactions.csv"))
        nav_history = pd.read_csv(path("nav_history.csv"))
    except FileNotFoundError as exc:
        raise SystemExit(f"Required data file not found: {exc.filename}")
    except pd.errors.EmptyDataError as exc:
        raise SystemExit(f"A data file is empty: {exc}")
    print("funds.csv        ->", funds.shape)
    print("investors.csv    ->", investors.shape)
    print("transactions.csv ->", transactions.shape)
    print("nav_history.csv  ->", nav_history.shape)
    return funds, investors, transactions, nav_history


def clean_data(funds, investors, transactions, nav_history):
    section("PART 2 - DATA CLEANING")
    for df in (funds, investors, transactions, nav_history):
        strip_object_columns(df)

    before = (len(funds), len(investors), len(transactions), len(nav_history))
    funds = funds.drop_duplicates()
    investors = investors.drop_duplicates()
    transactions = transactions.drop_duplicates()
    nav_history = nav_history.drop_duplicates()
    after = (len(funds), len(investors), len(transactions), len(nav_history))
    print("Duplicate rows removed (funds, investors, transactions, nav):",
          tuple(b - a for b, a in zip(before, after)))

    print("\nMissing values BEFORE cleaning:")
    for name, df in [("funds", funds), ("investors", investors),
                     ("transactions", transactions), ("nav_history", nav_history)]:
        print(f"  {name}: {df.isnull().sum().sum()} total")

    transactions["UnitsPurchased"] = pd.to_numeric(transactions["UnitsPurchased"], errors="coerce")
    transactions["PurchaseNAV"] = pd.to_numeric(transactions["PurchaseNAV"], errors="coerce")
    nav_history["NAV"] = pd.to_numeric(nav_history["NAV"], errors="coerce")

    nav_history["Date"] = pd.to_datetime(nav_history["Date"], errors="coerce")
    transactions["PurchaseDate"] = pd.to_datetime(transactions["PurchaseDate"], errors="coerce")

    nav_history = nav_history.sort_values(["FundID", "Date"])
    nav_history["NAV"] = nav_history.groupby("FundID")["NAV"].ffill()
    nav_history["NAV"] = nav_history.groupby("FundID")["NAV"].bfill()

    investors["InvestorType"] = investors["InvestorType"].fillna("Retail")

    neg_before = len(nav_history)
    nav_history = nav_history[nav_history["NAV"] >= 0]
    print(f"\nNegative-NAV rows removed: {neg_before - len(nav_history)}")

    print("\nMissing values AFTER cleaning:")
    for name, df in [("funds", funds), ("investors", investors),
                     ("transactions", transactions), ("nav_history", nav_history)]:
        print(f"  {name}: {df.isnull().sum().sum()} total")
    return funds, investors, transactions, nav_history


def merge_data(funds, investors, transactions, nav_history):
    section("PART 3 - MERGE DATA")
    latest_nav = (nav_history.sort_values("Date")
                  .groupby("FundID", as_index=False)
                  .last()[["FundID", "NAV"]]
                  .rename(columns={"NAV": "LatestNAV"}))

    df = (transactions
          .merge(investors, on="InvestorID", how="left")
          .merge(funds, on="FundID", how="left")
          .merge(latest_nav, on="FundID", how="left"))
    df["LatestNAV"] = df["LatestNAV"].fillna(df["PurchaseNAV"])

    df = df.rename(columns={
        "InvestorName": "Investor Name",
        "FundName": "Fund Name",
        "UnitsPurchased": "Units Purchased",
        "PurchaseNAV": "Purchase NAV",
        "LatestNAV": "Latest NAV",
    })

    required = ["Investor Name", "Fund Name", "Category", "AMC", "State",
                "Units Purchased", "Purchase NAV", "Latest NAV"]
    print("Merged DataFrame shape:", df.shape)
    print("\nRequired columns preview:")
    print(df[required].head(10).to_string(index=False))
    return df


def add_calculated_columns(df):
    section("PART 4 - CREATE NEW COLUMNS")
    df["Investment Amount"] = df["Units Purchased"] * df["Purchase NAV"]
    df["Current Value"] = df["Units Purchased"] * df["Latest NAV"]
    df["Profit"] = df["Current Value"] - df["Investment Amount"]
    df["ROI %"] = (df["Profit"] / df["Investment Amount"]) * 100
    print(df[["Fund Name", "Investment Amount", "Current Value",
              "Profit", "ROI %"]].head(10).round(2).to_string(index=False))
    return df


def numpy_tasks(nav_history):
    section("PART 5 - NUMPY TASKS")
    nav = nav_history["NAV"].to_numpy(dtype=float)
    print(f"Average NAV           : {np.mean(nav):.4f}")
    print(f"Maximum NAV           : {np.max(nav):.4f}")
    print(f"Minimum NAV           : {np.min(nav):.4f}")
    print(f"Variance of NAV       : {np.var(nav):.4f}")
    print(f"Standard Deviation    : {np.std(nav):.4f}")

    rolling = (nav_history.sort_values(["FundID", "Date"])
               .groupby("FundID")["NAV"]
               .transform(lambda s: s.rolling(window=5).mean()))
    nav_history = nav_history.assign(RollingAvg_5=rolling)
    print("\nRolling Average (window=5) sample:")
    print(nav_history.sort_values(["FundID", "Date"])
          [["FundID", "Date", "NAV", "RollingAvg_5"]].head(10).to_string(index=False))
    return nav_history


def pandas_analysis(df, nav_history):
    section("PART 6 - PANDAS ANALYSIS")
    print("Top 5 investors by investment amount:")
    top_investors = (df.groupby("Investor Name")["Investment Amount"]
                     .sum().sort_values(ascending=False).head(5))
    print(top_investors.round(2).to_string())

    print("\nTop 5 profitable funds:")
    top_funds = (df.groupby("Fund Name")["Profit"]
                 .sum().sort_values(ascending=False).head(5))
    print(top_funds.round(2).to_string())

    print("\nWorst performing fund (lowest total profit):")
    worst = df.groupby("Fund Name")["Profit"].sum().sort_values().head(1)
    print(worst.round(2).to_string())

    latest_nav = (nav_history.sort_values("Date")
                  .groupby("FundID", as_index=False).last()[["FundID", "NAV"]])
    highest = latest_nav.loc[latest_nav["NAV"].idxmax()]
    lowest = latest_nav.loc[latest_nav["NAV"].idxmin()]
    print(f"\nHighest NAV fund : {highest['FundID']}  NAV={highest['NAV']:.2f}")
    print(f"Lowest NAV fund  : {lowest['FundID']}  NAV={lowest['NAV']:.2f}")
    return top_investors, top_funds


def groupby_analysis(df):
    section("PART 7 - GROUPBY")
    by_category = df.groupby("Category").agg(
        Average_ROI=("ROI %", "mean"),
        Average_NAV=("Latest NAV", "mean"),
        Total_Investment=("Investment Amount", "sum"),
    ).round(2)
    print("Group by CATEGORY:")
    print(by_category.to_string())

    by_amc = df.groupby("AMC").agg(
        Number_of_Funds=("Fund Name", "nunique"),
        Average_NAV=("Latest NAV", "mean"),
        Total_Investment=("Investment Amount", "sum"),
    ).round(2)
    print("\nGroup by AMC:")
    print(by_amc.to_string())

    by_state = df.groupby("State").agg(
        Number_of_Investors=("Investor Name", "nunique"),
        Total_Investment=("Investment Amount", "sum"),
        Average_ROI=("ROI %", "mean"),
    ).round(2)
    print("\nGroup by STATE:")
    print(by_state.to_string())

    by_type = df.groupby("InvestorType").agg(
        Total_Investment=("Investment Amount", "sum"),
        Average_Profit=("Profit", "mean"),
    ).round(2)
    print("\nGroup by INVESTOR TYPE:")
    print(by_type.to_string())
    return by_category, by_amc, by_state, by_type


def detect_issues(funds, investors, transactions, nav_history_raw):
    section("PART 8 - DETECT ISSUES")
    dup_nav = nav_history_raw.duplicated(subset=["FundID", "Date"]).sum()
    print(f"Duplicate NAV records (FundID+Date) : {dup_nav}")

    neg_nav = (nav_history_raw["NAV"] < 0).sum()
    print(f"Negative NAV                        : {neg_nav}")

    today = pd.Timestamp.now().normalize()
    future_dates = (nav_history_raw["Date"] > today).sum()
    future_tx = (transactions["PurchaseDate"] > today).sum()
    print(f"Future dates (nav_history)          : {future_dates}")
    print(f"Future dates (transactions)         : {future_tx}")

    missing_fund_ids = (~transactions["FundID"].isin(funds["FundID"])).sum()
    print(f"Missing Fund IDs (not in funds.csv) : {missing_fund_ids}")

    missing_inv_ids = (~transactions["InvestorID"].isin(investors["InvestorID"])).sum()
    print(f"Missing Investor IDs                : {missing_inv_ids}")

    invalid_nav = (transactions["PurchaseNAV"] < 0).sum()
    print(f"Invalid Purchase NAV (<0)           : {invalid_nav}")


def finance_metrics(df, nav_history):
    section("PART 9 - FINANCE METRICS")
    RISK_FREE_RATE = 6.0

    df["ROI"] = (df["Current Value"] - df["Investment Amount"]) / df["Investment Amount"] * 100
    df["Absolute Return"] = df["Current Value"] - df["Investment Amount"]
    df["Annual Return"] = df["ROI"]

    volatility = (nav_history.groupby("FundID")["NAV"].std()
                  .rename("Volatility").reset_index())

    print("Sample per-transaction finance metrics:")
    print(df[["Fund Name", "ROI", "Absolute Return", "Annual Return"]]
          .head(10).round(2).to_string(index=False))

    fund_return = df.groupby("FundID")["ROI"].mean().rename("Return").reset_index()
    sharpe = fund_return.merge(volatility, on="FundID", how="left")
    sharpe["Sharpe Ratio"] = (sharpe["Return"] - RISK_FREE_RATE) / sharpe["Volatility"]
    print("\nVolatility & Sharpe Ratio per fund (sample):")
    print(sharpe.head(10).round(3).to_string(index=False))
    return df, sharpe


def export_reports(df, by_category, by_amc, by_state):
    section("PART 10 - EXPORT REPORTS")
    top_funds = df.groupby("Fund Name").agg(
        Highest_ROI=("ROI %", "max"),
        Total_Profit=("Profit", "sum"),
        Latest_NAV=("Latest NAV", "max"),
        Total_Investment=("Investment Amount", "sum"),
    ).sort_values("Total_Profit", ascending=False).round(2)

    top_funds_path = path("TopFunds.xlsx")
    top_funds.to_excel(top_funds_path, sheet_name="TopFunds")
    print("Written:", top_funds_path)

    investor_summary = df.groupby("Investor Name").agg(
        Total_Investment=("Investment Amount", "sum"),
        Current_Value=("Current Value", "sum"),
        Total_Profit=("Profit", "sum"),
        Average_ROI=("ROI %", "mean"),
    ).sort_values("Total_Investment", ascending=False).round(2)

    investor_path = path("InvestorSummary.xlsx")
    investor_summary.to_excel(investor_path, sheet_name="InvestorSummary")
    print("Written:", investor_path)

    category_path = path("CategorySummary.csv")
    by_category.to_csv(category_path)
    print("Written:", category_path)

    section("EXPECTED OUTPUTS")
    best = top_funds.head(1)
    print("Top Performing Fund (Highest Profit):")
    print(best.to_string())

    worst = df.groupby("Fund Name")["ROI %"].mean().sort_values().head(1)
    print("\nWorst Performing Fund (Lowest ROI):")
    print(worst.round(2).to_string())

    print("\nState-wise Investment:")
    print(by_state["Total_Investment"].round(2).to_string())

    print("\nAMC-wise Investment:")
    print(by_amc["Total_Investment"].round(2).to_string())

    print("\nCategory-wise ROI:")
    print(by_category["Average_ROI"].round(2).to_string())


def main():
    funds, investors, transactions, nav_history = read_data()
    funds, investors, transactions, nav_history = clean_data(
        funds, investors, transactions, nav_history)

    df = merge_data(funds, investors, transactions, nav_history)
    df = add_calculated_columns(df)

    nav_history = numpy_tasks(nav_history)
    pandas_analysis(df, nav_history)
    by_category, by_amc, by_state, by_type = groupby_analysis(df)
    detect_issues(funds, investors, transactions, nav_history)
    df, sharpe = finance_metrics(df, nav_history)
    export_reports(df, by_category, by_amc, by_state)

    section("DONE - ALL 10 PARTS COMPLETED")


if __name__ == "__main__":
    main()
