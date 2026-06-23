package Payment;

public interface PaymentService {
    void pay(double amount);
    void pay(double amount, String description);
}
