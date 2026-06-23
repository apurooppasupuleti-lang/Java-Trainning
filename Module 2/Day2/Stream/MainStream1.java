

import java.util.ArrayList;
import java.util.List;

public class MainStream1 {
    public static void main(String[] args) {

        List<Loan> loans = new ArrayList<>();

        loans.add(new Loan(1, 1000, 12, 10, "Approved", "home"));
        loans.add(new Loan(2, 2000, 24, 12, "Approved", "personal"));
        loans.add(new Loan(3, 3000, 36, 15, "Rejected", "home"));
        loans.add(new Loan(4, 4000, 48, 18, "Approved", "personal"));
        loans.add(new Loan(5, 50000, 60, 20, "Rejected", "Gold"));
        loans.add(new Loan(6, 6000, 72, 25, "Approved", "Business"));

        List<Loan> newLoans = loans.stream()
    .filter(loan -> loan.getLoanType().equals("home"))
    .map(loan -> {
        System.out.println("mapping called for:" + loan);
        loan.setLoanInterest(loan.getLoanInterest() + 2);
        return loan;
    })
    .toList();

          System.out.println("New Loans:");
          System.out.println(newLoans);
}
}