import os
import numpy as np
import pandas as pd

BASE_DIR = os.path.dirname(os.path.abspath(__file__))
TWENTY_LAKHS = 2_000_000
THIRTY_LAKHS = 3_000_000


def path(filename):
    return os.path.join(BASE_DIR, filename)


def section(title):
    print("\n" + "=" * 70)
    print(title)
    print("=" * 70)


def strip_text_columns(df):
    for col in df.columns:
        if df[col].dtype == object or pd.api.types.is_string_dtype(df[col]):
            df[col] = df[col].astype(str).str.strip()
    return df


def read_data():
    section("PART 1 - READ DATA")
    try:
        customers = pd.read_csv(path("customers.csv"))
        applications = pd.read_csv(path("loan_application.csv"))
        payments = pd.read_csv(path("loan_payments.csv"))
    except FileNotFoundError as exc:
        raise SystemExit(f"Required data file not found: {exc.filename}")
    except pd.errors.EmptyDataError as exc:
        raise SystemExit(f"A data file is empty: {exc}")
    print("customers.csv         ->", customers.shape)
    print("loan_application.csv  ->", applications.shape)
    print("loan_payments.csv     ->", payments.shape)
    return customers, applications, payments


def clean_data(customers, applications, payments):
    section("PART 2 - DATA CLEANING")
    for df in (customers, applications, payments):
        strip_text_columns(df)

    customers = customers.drop_duplicates()
    applications = applications.drop_duplicates().drop_duplicates(subset=["LoanID"])
    payments = payments.drop_duplicates().drop_duplicates(subset=["LoanID"])

    print("Missing values BEFORE cleaning:")
    for name, df in [("customers", customers), ("applications", applications),
                     ("payments", payments)]:
        print(f"  {name}: {int(df.isnull().sum().sum())} total")

    customers["Salary"] = pd.to_numeric(customers["Salary"], errors="coerce")
    applications["LoanAmount"] = pd.to_numeric(applications["LoanAmount"], errors="coerce")
    payments["EMIAmount"] = pd.to_numeric(payments["EMIAmount"], errors="coerce")
    payments["PaidEMIs"] = pd.to_numeric(payments["PaidEMIs"], errors="coerce")
    payments["PendingEMIs"] = pd.to_numeric(payments["PendingEMIs"], errors="coerce")

    customers["Salary"] = customers["Salary"].fillna(customers["Salary"].median())

    applications["ApplicationDate"] = pd.to_datetime(applications["ApplicationDate"], errors="coerce")
    payments["LastPaymentDate"] = pd.to_datetime(payments["LastPaymentDate"], errors="coerce")

    neg_loans = int((applications["LoanAmount"] < 0).sum())
    applications = applications[applications["LoanAmount"] >= 0]

    invalid_emi = int((~(payments["EMIAmount"] > 0)).sum())
    payments = payments[payments["EMIAmount"] > 0]

    today = pd.Timestamp.now().normalize()
    future_pay = int((payments["LastPaymentDate"] > today).sum())
    payments = payments[payments["LastPaymentDate"].isna() | (payments["LastPaymentDate"] <= today)]

    print(f"\nNegative loan amounts removed : {neg_loans}")
    print(f"Invalid EMI amounts removed   : {invalid_emi}")
    print(f"Future payment dates removed  : {future_pay}")

    print("\nMissing values AFTER cleaning:")
    for name, df in [("customers", customers), ("applications", applications),
                     ("payments", payments)]:
        print(f"  {name}: {int(df.isnull().sum().sum())} total")
    return customers, applications, payments


def derive_credit_score(df):
    repayment_ratio = df["PaidEMIs"] / (df["PaidEMIs"] + df["PendingEMIs"]).replace(0, np.nan)
    repayment_ratio = repayment_ratio.fillna(0)
    salary = df["Salary"].astype(float)
    salary_norm = (salary - salary.min()) / (salary.max() - salary.min() + 1e-9)
    score = 300 + repayment_ratio * 400 + salary_norm * 200
    df["CreditScore"] = score.round().clip(300, 900).astype(float)
    return df


def merge_datasets(customers, applications, payments):
    section("PART 3 - MERGE DATASETS")
    customers = customers.copy()
    applications = applications.copy()
    payments = payments.copy()

    customers["CustKey"] = customers["CustomerID"].astype(str).str.extract(r"(\d+)").astype(int)
    applications["CustKey"] = pd.to_numeric(
        applications["CustomerID"].astype(str).str.extract(r"(\d+)")[0], errors="coerce").astype("Int64")
    applications["LoanKey"] = applications["LoanID"].astype(str).str[2:].astype(int)
    payments["LoanKey"] = payments["LoanID"].astype(str).str[2:].astype(int)

    merged = (applications
              .merge(customers, on="CustKey", how="left", suffixes=("", "_cust"))
              .merge(payments, on="LoanKey", how="left", suffixes=("", "_pay")))

    merged["Amount Paid"] = merged["PaidEMIs"] * merged["EMIAmount"]
    merged["Total Payable"] = (merged["PaidEMIs"] + merged["PendingEMIs"]) * merged["EMIAmount"]
    merged["Payment Status"] = np.where(
        merged["PendingEMIs"] == 0, "Paid",
        np.where(merged["PaidEMIs"] == 0, "Pending", "Partial"))

    merged = derive_credit_score(merged)
    merged["CreditScore"] = merged["CreditScore"].fillna(merged["CreditScore"].mean())

    merged = merged.rename(columns={
        "CustomerName": "Customer Name",
        "LoanType": "Loan Type",
        "LoanAmount": "Loan Amount",
        "CreditScore": "Credit Score",
        "LoanStatus": "Loan Status",
        "EMIAmount": "EMI Amount",
    })

    required = ["Customer Name", "City", "Loan Type", "Loan Amount", "Credit Score",
                "Salary", "Loan Status", "EMI Amount", "Payment Status"]
    print("Merged DataFrame shape:", merged.shape)
    print("\nRequired columns preview:")
    print(merged[required].head(10).to_string(index=False))
    return merged


def add_calculated_columns(df):
    section("PART 4 - CREATE NEW COLUMNS")
    df["Monthly Income"] = df["Salary"] / 12
    df["Debt-to-Income Ratio"] = df["Loan Amount"] / df["Salary"]
    df["EMI Due"] = df["Total Payable"] - df["Amount Paid"]
    df["Payment Completion %"] = np.where(
        df["Total Payable"] > 0, df["Amount Paid"] / df["Total Payable"] * 100, 0)
    print(df[["Customer Name", "Monthly Income", "Debt-to-Income Ratio",
              "EMI Due", "Payment Completion %"]].head(10).round(2).to_string(index=False))
    return df


def numpy_tasks(df):
    section("PART 5 - NUMPY TASKS")
    amounts = df["Loan Amount"].to_numpy(dtype=float)
    print(f"Average Loan Amount     : {np.mean(amounts):,.2f}")
    print(f"Median Loan Amount      : {np.median(amounts):,.2f}")
    print(f"Maximum Loan Amount     : {np.max(amounts):,.2f}")
    print(f"Minimum Loan Amount     : {np.min(amounts):,.2f}")
    print(f"Standard Deviation      : {np.std(amounts):,.2f}")
    print(f"Variance                : {np.var(amounts):,.2f}")
    print(f"25th Percentile         : {np.percentile(amounts, 25):,.2f}")
    print(f"75th Percentile         : {np.percentile(amounts, 75):,.2f}")


def pandas_analysis(df):
    section("PART 6 - PANDAS ANALYSIS")
    print("Top 10 highest loan customers:")
    print(df.sort_values("Loan Amount", ascending=False)
          [["Customer Name", "Loan Amount"]].head(10).to_string(index=False))

    print("\nTop 10 customers by salary:")
    print(df.sort_values("Salary", ascending=False)
          [["Customer Name", "Salary"]].head(10).to_string(index=False))

    print("\nCustomers with Credit Score below 650:")
    low_credit = df[df["Credit Score"] < 650]
    print(low_credit[["Customer Name", "Credit Score"]].to_string(index=False)
          if not low_credit.empty else "  (none)")

    print("\nCustomers with Loan Amount greater than Rs.20 Lakhs:")
    big_loans = df[df["Loan Amount"] > TWENTY_LAKHS]
    print(big_loans[["Customer Name", "Loan Amount"]].to_string(index=False)
          if not big_loans.empty else "  (none)")

    print("\nLoans with Pending Payments:")
    print(f"  Count: {len(df[df['Payment Status'].isin(['Pending', 'Partial'])])}")

    print("\nFully Paid Loans:")
    print(f"  Count: {len(df[df['Payment Status'] == 'Paid'])}")


def groupby_analysis(df):
    section("PART 7 - GROUPBY")
    by_city = df.groupby("City").agg(
        Number_of_Customers=("Customer Name", "nunique"),
        Average_Salary=("Salary", "mean"),
        Total_Loan_Amount=("Loan Amount", "sum"),
    ).round(2)
    print("Group by CITY:")
    print(by_city.to_string())

    by_type = df.groupby("Loan Type").agg(
        Number_of_Loans=("LoanID", "count"),
        Average_Loan_Amount=("Loan Amount", "mean"),
        Total_Loan_Amount=("Loan Amount", "sum"),
    ).round(2)
    print("\nGroup by LOAN TYPE:")
    print(by_type.to_string())

    by_status = df.groupby("Loan Status").size().rename("Count")
    print("\nGroup by LOAN STATUS:")
    print(by_status.to_string())
    print(f"  Approved: {int((df['Loan Status'] == 'Approved').sum())}")
    print(f"  Pending : {int((df['Loan Status'] == 'Pending').sum())}")
    print(f"  Rejected: {int((df['Loan Status'] == 'Rejected').sum())}")

    by_payment = df.groupby("Payment Status").agg(
        Count=("LoanID", "count"),
        Total_Amount_Paid=("Amount Paid", "sum"),
    ).round(2)
    print("\nGroup by PAYMENT STATUS:")
    print(by_payment.to_string())
    return by_city, by_type, by_status, by_payment


def business_rules(df):
    section("PART 8 - BUSINESS RULES")
    df["Flag_LoanAmount_gt_30L"] = df["Loan Amount"] > THIRTY_LAKHS
    df["Flag_CreditScore_lt_650"] = df["Credit Score"] < 650
    df["Flag_Salary_lt_30000"] = df["Salary"] < 30000
    df["Flag_DTI_gt_5"] = df["Debt-to-Income Ratio"] > 5
    df["Flag_EMIDue_gt_10000"] = df["EMI Due"] > 10000
    df["Flag_Payment_Pending"] = df["Payment Status"] == "Pending"
    df["Flag_Loan_Rejected"] = df["Loan Status"] == "Rejected"

    flag_cols = [c for c in df.columns if c.startswith("Flag_")]
    df["Flag_Count"] = df[flag_cols].sum(axis=1)
    df["Flagged"] = df["Flag_Count"] > 0

    print("Flag counts:")
    for col in flag_cols:
        print(f"  {col:26s}: {int(df[col].sum())}")
    print(f"\nTotal flagged loans: {int(df['Flagged'].sum())} of {len(df)}")
    return df


def finance_metrics(df):
    section("PART 9 - FINANCE METRICS")
    total_portfolio = float(df["Loan Amount"].sum())
    total_collected = float(df["Amount Paid"].sum())
    total_loans = len(df)
    pending_loans = int((df["Loan Status"] == "Pending").sum())

    metrics = {
        "Total Loan Portfolio": round(total_portfolio, 2),
        "Total Amount Collected": round(total_collected, 2),
        "Outstanding Amount": round(total_portfolio - total_collected, 2),
        "Loan Recovery %": round(total_collected / total_portfolio * 100, 2) if total_portfolio else 0.0,
        "Default %": round(pending_loans / total_loans * 100, 2) if total_loans else 0.0,
        "Average EMI": round(float(df["EMI Amount"].mean()), 2),
        "Average Credit Score": round(float(df["Credit Score"].mean()), 2),
    }
    for k, v in metrics.items():
        print(f"  {k:24s}: {v:,}")
    return metrics


def export_reports(df, by_city, by_type, metrics):
    section("PART 10 - EXPORT REPORTS")
    loan_summary_path = path("LoanSummary.xlsx")
    finance_df = pd.DataFrame(list(metrics.items()), columns=["Metric", "Value"])
    with pd.ExcelWriter(loan_summary_path, engine="openpyxl") as writer:
        by_city.to_excel(writer, sheet_name="City_Summary")
        by_type.to_excel(writer, sheet_name="LoanType_Summary")
        finance_df.to_excel(writer, sheet_name="Finance_Metrics", index=False)
    print("Written:", loan_summary_path)

    report_cols = [c for c in ["CustomerID", "Customer Name", "City", "Loan Type", "Loan Amount",
                               "Credit Score", "Salary", "Loan Status", "EMI Amount", "Amount Paid",
                               "EMI Due", "Payment Status", "Payment Completion %", "Flagged"]
                   if c in df.columns]
    customer_report_path = path("CustomerLoanReport.xlsx")
    df[report_cols].to_excel(customer_report_path, sheet_name="CustomerLoanReport", index=False)
    print("Written:", customer_report_path)

    pending = df[df["Payment Status"].isin(["Pending", "Partial"])]
    pending_cols = [c for c in ["CustomerID", "Customer Name", "LoanID", "Loan Type",
                                "EMI Amount", "Amount Paid", "EMI Due", "Payment Status"]
                    if c in df.columns]
    pending_path = path("PendingPayments.csv")
    pending[pending_cols].to_csv(pending_path, index=False)
    print("Written:", pending_path)

    section("EXPECTED OUTPUTS")
    print("Top 10 Loan Customers:")
    print(df.sort_values("Loan Amount", ascending=False)
          [["Customer Name", "Loan Amount"]].head(10).to_string(index=False))

    print("\nCustomers with Low Credit Score (<650):")
    low = df[df["Credit Score"] < 650][["Customer Name", "Credit Score"]]
    print(low.to_string(index=False) if not low.empty else "  (none)")

    print("\nPending Loan Payments:")
    print(pending[pending_cols].to_string(index=False) if not pending.empty else "  (none)")

    print("\nCity-wise Loan Summary:")
    print(by_city["Total_Loan_Amount"].round(2).to_string())

    print("\nLoan Type Summary:")
    print(by_type["Total_Loan_Amount"].round(2).to_string())

    print("\nLoan Recovery Report:")
    print(f"  Total Portfolio : {metrics['Total Loan Portfolio']:,}")
    print(f"  Total Collected : {metrics['Total Amount Collected']:,}")
    print(f"  Outstanding     : {metrics['Outstanding Amount']:,}")
    print(f"  Recovery %      : {metrics['Loan Recovery %']}")


def main():
    customers, applications, payments = read_data()
    customers, applications, payments = clean_data(customers, applications, payments)

    df = merge_datasets(customers, applications, payments)
    df = add_calculated_columns(df)

    numpy_tasks(df)
    pandas_analysis(df)
    by_city, by_type, by_status, by_payment = groupby_analysis(df)
    df = business_rules(df)
    metrics = finance_metrics(df)
    export_reports(df, by_city, by_type, metrics)

    section("DONE - ALL 10 PARTS COMPLETED")


if __name__ == "__main__":
    main()
