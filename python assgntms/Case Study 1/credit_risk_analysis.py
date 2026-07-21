from __future__ import annotations

import json
import os
from dataclasses import dataclass, field

import numpy as np
import pandas as pd

BASE_DIR = os.path.dirname(os.path.abspath(__file__))
CUSTOMERS_FILE = os.path.join(BASE_DIR, "customers.csv")
LOANS_FILE = os.path.join(BASE_DIR, "loans.csv")
CREDIT_SCORE_CANDIDATES = [
    os.path.join(BASE_DIR, "credit_scores.csv"),
    os.path.join(BASE_DIR, "credit_score.csv"),
]
TEN_LAKHS = 1_000_000
PD_DEFAULTED = 1.00
PD_PERFORMING = 0.05
LGD = 0.45


@dataclass
class Loan:
    loan_id: str
    customer_id: int
    loan_amount: float
    interest_rate: float
    tenure: int
    emi: float
    paid_emis: int
    default_flag: int
    salary: float = np.nan
    credit_score: float = np.nan
    outstanding_emis: int = field(init=False, default=0)
    outstanding_amount: float = field(init=False, default=0.0)

    def __post_init__(self):
        self.outstanding_emis = max(int(self.tenure) - int(self.paid_emis), 0)
        self.outstanding_amount = self.outstanding_emis * float(self.emi)

    def debt_to_income(self):
        if not self.salary or np.isnan(self.salary) or self.salary == 0:
            return np.nan
        return round(self.emi / self.salary, 4)

    def loan_utilization(self):
        if self.tenure == 0:
            return np.nan
        return round(self.outstanding_emis / self.tenure, 4)

    def is_npa(self):
        return self.default_flag == 1 and self.outstanding_amount > 0

    def expected_loss(self):
        pd_value = PD_DEFAULTED if self.default_flag == 1 else PD_PERFORMING
        return round(pd_value * LGD * self.outstanding_amount, 2)

    def risk_score(self):
        score = 0.0
        if not np.isnan(self.credit_score):
            score += np.clip((850 - self.credit_score) / 850, 0, 1) * 40
        dti = self.debt_to_income()
        if not np.isnan(dti):
            score += np.clip(dti / 0.5, 0, 1) * 25
        util = self.loan_utilization()
        if not np.isnan(util):
            score += util * 15
        score += 20 if self.default_flag == 1 else 0
        return round(float(score), 2)


def read_csv_safe(path, required_columns=None):
    try:
        df = pd.read_csv(path, on_bad_lines="skip", skip_blank_lines=True)
    except FileNotFoundError:
        raise FileNotFoundError(f"Input file not found: {path}")
    except pd.errors.EmptyDataError:
        raise ValueError(f"Input file is empty or corrupted: {path}")
    except pd.errors.ParserError as exc:
        raise ValueError(f"Failed to parse (corrupted) file '{path}': {exc}")
    except UnicodeDecodeError as exc:
        raise ValueError(f"Encoding error reading '{path}': {exc}")
    if df.empty:
        raise ValueError(f"No usable rows found in file: {path}")
    if required_columns:
        missing = [c for c in required_columns if c not in df.columns]
        if missing:
            raise ValueError(f"File '{path}' is missing required columns: {missing}")
    return df


def resolve_credit_score_file(candidates):
    for path in candidates:
        if os.path.exists(path):
            return path
    raise FileNotFoundError("Could not locate a credit score file. Tried: " + ", ".join(candidates))


def load_data():
    customers = read_csv_safe(CUSTOMERS_FILE, ["CustomerID", "Age", "Salary", "City"])
    loans = read_csv_safe(LOANS_FILE, [
        "LoanID", "CustomerID", "LoanAmount", "InterestRate",
        "Tenure", "EMI", "PaidEMIs", "DefaultFlag",
    ])
    credit_scores = read_csv_safe(resolve_credit_score_file(CREDIT_SCORE_CANDIDATES),
                                  ["CustomerID", "CreditScore"])
    return customers, loans, credit_scores


def numpy_statistics(merged, percentile=90.0):
    loan_amounts = merged["LoanAmount"].to_numpy(dtype=float)
    salaries = merged["Salary"].to_numpy(dtype=float)
    interest_rates = merged["InterestRate"].to_numpy(dtype=float)
    return {
        "mean_loan_amount": float(np.mean(loan_amounts)),
        "median_salary": float(np.median(salaries)),
        f"p{int(percentile)}_interest_rate": float(np.percentile(interest_rates, percentile)),
        "corr_salary_loan_amount": float(np.corrcoef(salaries, loan_amounts)[0, 1]),
        "std_loan_amount": float(np.std(loan_amounts)),
        "std_salary": float(np.std(salaries)),
        "std_interest_rate": float(np.std(interest_rates)),
    }


def merge_datasets(customers, loans, credit_scores):
    return loans.merge(customers, on="CustomerID", how="left").merge(
        credit_scores, on="CustomerID", how="left")


def handle_missing_data(df):
    df = df.copy()
    if df["Salary"].isna().any():
        df["Salary"] = df["Salary"].fillna(df["Salary"].median())
    if df["CreditScore"].isna().any():
        df["CreditScore"] = df["CreditScore"].fillna(df["CreditScore"].mean())
    if df["InterestRate"].isna().any():
        df["InterestRate"] = df["InterestRate"].ffill().bfill()
    return df


def remove_outliers(df):
    threshold = float(np.percentile(df["LoanAmount"].to_numpy(dtype=float), 99))
    cleaned = df[df["LoanAmount"] <= threshold].copy()
    return cleaned, threshold, len(df) - len(cleaned)


def build_loan_objects(df):
    return [
        Loan(
            loan_id=row.LoanID,
            customer_id=int(row.CustomerID),
            loan_amount=float(row.LoanAmount),
            interest_rate=float(row.InterestRate),
            tenure=int(row.Tenure),
            emi=float(row.EMI),
            paid_emis=int(row.PaidEMIs),
            default_flag=int(row.DefaultFlag),
            salary=float(row.Salary),
            credit_score=float(row.CreditScore),
        )
        for row in df.itertuples(index=False)
    ]


def enrich_with_metrics(df, loans):
    df = df.copy()
    df["DebtToIncome"] = [ln.debt_to_income() for ln in loans]
    df["LoanUtilization"] = [ln.loan_utilization() for ln in loans]
    df["OutstandingAmount"] = [ln.outstanding_amount for ln in loans]
    df["IsNPA"] = [ln.is_npa() for ln in loans]
    df["ExpectedLoss"] = [ln.expected_loss() for ln in loans]
    df["RiskScore"] = [ln.risk_score() for ln in loans]
    return df


def find_top_risky(df, n=20):
    return df.sort_values("RiskScore", ascending=False).head(n)


def find_high_risk_segment(df):
    mask = (
        (df["CreditScore"] < 650)
        & (df["Salary"] < 60000)
        & (df["LoanAmount"] > TEN_LAKHS)
        & (df["DefaultFlag"] == 1)
    )
    return df[mask].copy()


def find_condition_segments(df):
    return {
        "credit_score_lt_650": df[df["CreditScore"] < 650].copy(),
        "salary_lt_60000": df[df["Salary"] < 60000].copy(),
        "loan_gt_10lakh": df[df["LoanAmount"] > TEN_LAKHS].copy(),
        "default_flag_1": df[df["DefaultFlag"] == 1].copy(),
    }


def portfolio_finance_metrics(df):
    total_loans = len(df)
    default_count = int((df["DefaultFlag"] == 1).sum())
    npa_count = int(df["IsNPA"].sum())
    return {
        "total_loans": total_loans,
        "total_loan_exposure": round(float(df["LoanAmount"].sum()), 2),
        "total_outstanding_amount": round(float(df["OutstandingAmount"].sum()), 2),
        "avg_debt_to_income": round(float(df["DebtToIncome"].mean()), 4),
        "avg_loan_utilization": round(float(df["LoanUtilization"].mean()), 4),
        "default_pct": round(default_count / total_loans * 100, 2) if total_loans else 0.0,
        "npa_pct": round(npa_count / total_loans * 100, 2) if total_loans else 0.0,
        "average_emi": round(float(df["EMI"].mean()), 2),
        "total_expected_loss": round(float(df["ExpectedLoss"].sum()), 2),
    }


def generate_outputs(merged_clean, top_risky, high_risk_segment, condition_segments,
                     numpy_stats, finance_metrics, outlier_info):
    paths = {
        "excel": os.path.join(BASE_DIR, "risk_report.xlsx"),
        "csv": os.path.join(BASE_DIR, "high_risk_customers.csv"),
        "json": os.path.join(BASE_DIR, "summary.json"),
    }
    csv_frame = high_risk_segment if not high_risk_segment.empty else top_risky
    csv_frame.to_csv(paths["csv"], index=False)

    stats_df = pd.DataFrame(list(numpy_stats.items()), columns=["Metric", "Value"])
    finance_df = pd.DataFrame(list(finance_metrics.items()), columns=["Metric", "Value"])
    try:
        with pd.ExcelWriter(paths["excel"], engine="openpyxl") as writer:
            merged_clean.to_excel(writer, sheet_name="Portfolio", index=False)
            top_risky.to_excel(writer, sheet_name="Top20_Risky", index=False)
            csv_frame.to_excel(writer, sheet_name="High_Risk_Segment", index=False)
            stats_df.to_excel(writer, sheet_name="NumPy_Stats", index=False)
            finance_df.to_excel(writer, sheet_name="Finance_Metrics", index=False)
    except ImportError:
        paths["excel"] = paths["excel"].replace(".xlsx", "_portfolio.csv")
        merged_clean.to_csv(paths["excel"], index=False)

    summary = {
        "numpy_statistics": numpy_stats,
        "finance_metrics": finance_metrics,
        "outlier_removal": outlier_info,
        "segment_counts": {name: int(len(frame)) for name, frame in condition_segments.items()},
        "high_risk_segment_count": int(len(high_risk_segment)),
        "top_20_risky_customer_ids": [int(cid) for cid in top_risky["CustomerID"].tolist()],
    }
    with open(paths["json"], "w", encoding="utf-8") as fh:
        json.dump(summary, fh, indent=4)
    return paths


def main():
    print("=" * 70)
    print("Credit Risk & Loan Portfolio Analysis")
    print("=" * 70)

    customers, loans, credit_scores = load_data()
    print(f"Loaded customers={len(customers)}, loans={len(loans)}, credit_scores={len(credit_scores)}")

    merged = merge_datasets(customers, loans, credit_scores)
    merged = handle_missing_data(merged)

    merged_clean, threshold, removed = remove_outliers(merged)
    outlier_info = {
        "loan_amount_99th_percentile": round(threshold, 2),
        "rows_removed": int(removed),
        "rows_remaining": int(len(merged_clean)),
    }
    print(f"Outliers removed: {removed} (LoanAmount > {threshold:,.0f})")

    numpy_stats = numpy_statistics(merged_clean, percentile=90.0)
    print("\nNumPy statistics:")
    for k, v in numpy_stats.items():
        print(f"  {k:28s}: {v:,.4f}")

    loan_objects = build_loan_objects(merged_clean)
    merged_clean = enrich_with_metrics(merged_clean, loan_objects)

    top_risky = find_top_risky(merged_clean, n=20)
    high_risk_segment = find_high_risk_segment(merged_clean)
    condition_segments = find_condition_segments(merged_clean)
    print("\nTop 20 risky customers identified.")
    print(f"High-risk segment (all 4 conditions): {len(high_risk_segment)} customers")
    for name, frame in condition_segments.items():
        print(f"  {name:24s}: {len(frame)}")

    finance_metrics = portfolio_finance_metrics(merged_clean)
    print("\nFinance metrics:")
    for k, v in finance_metrics.items():
        print(f"  {k:28s}: {v}")

    paths = generate_outputs(merged_clean, top_risky, high_risk_segment,
                             condition_segments, numpy_stats, finance_metrics, outlier_info)
    print("\nGenerated files:")
    for name, path in paths.items():
        print(f"  {name:6s}: {path}")
    print("\nDone.")


if __name__ == "__main__":
    main()
