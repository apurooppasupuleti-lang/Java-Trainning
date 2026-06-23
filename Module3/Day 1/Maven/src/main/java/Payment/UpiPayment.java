package Payment;

public class UpiPayment implements PaymentService {
    @Override
    public void pay(double amount) {
        System.out.println("Paying " + amount + " using UPI");
    }

    @Override
    public void pay(double amount, String description) {
        System.out.println("Paying " + amount + " for " + description + " using UPI");
    }
}
