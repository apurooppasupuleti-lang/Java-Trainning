package Payment;

public class DebitCard implements PaymentService {
    @Override
    public void pay(double amount) {
        System.out.println("Paying " + amount + " using Debit card");
    }

    @Override
    public void pay(double amount, String description) {
        System.out.println("Paying " + amount + " for " + description + " using Debit card");
    }
}