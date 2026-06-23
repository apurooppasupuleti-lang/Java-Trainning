

import java.util.ArrayList;
import java.util.List;

public class MainStream5 {
    public static void main(String[] args) {

        List<Loan> loans = new ArrayList<>();

        loans.add(new Loan(1, 1000, 12, 10, "Approved", "home"));
        loans.add(new Loan(2, 2000, 24, 12, "Approved", "personal"));
        loans.add(new Loan(3, 3000, 36, 15, "Rejected", "home"));
        loans.add(new Loan(4, 4000, 48, 18, "Approved", "personal"));
        loans.add(new Loan(5, 50000, 60, 20, "Rejected", "Gold"));
        loans.add(new Loan(6, 6000, 72, 25, "Rejected", "Business"));
        
        System.out.println(loans.stream().max((Loan l1,Loan l2)->l1.getLoanAmount()-l2.getLoanAmount()));
        System.out.println(loans.stream().min((Loan l1,Loan l2)->l1.getLoanAmount()-l2.getLoanAmount()));
        int totalAmount = loans.stream().mapToInt(Loan::getLoanAmount).reduce(0, (a, b) -> a + b);
        System.out.println("Total Loan Amount: " + totalAmount);

        loans.stream().forEach(loan -> System.out.println(loan));
        System.out.println("------------------------------");
        loans.stream().forEach(System.out::println);
        System.out.println(loans.stream().filter((loan)-> loan.getLoanStatus().equals("Rejected")));


}
}