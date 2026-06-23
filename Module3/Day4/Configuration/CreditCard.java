package Payment;

import org.springframework.stereotype.Component;

@Component("CreditCard")
public class CreditCard implements PaymentService {
    @Override
    public void pay(double amount){
        System.out.println("Paying " + amount + " Using credit card");
    }

    @Override
    public void pay(double amount, String description){
        System.out.println("Paying " + amount + " for " + description + " Using credit card");
    }
}
