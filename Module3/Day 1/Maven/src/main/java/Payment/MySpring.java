package Payment;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MySpring {

    @Bean("CreditCard")
    public PaymentService creditCardPaymentService(){
        return new CreditCard();
    }

    @Bean("DebitCard")
    public PaymentService debitCardPaymentService(){
        return new DebitCard();
    }

    @Bean("Upi")
    public PaymentService upiPaymentService(){
        return new UpiPayment();
    }

    @Bean("Email")
    public NotificationService emailNotificationService(){
        return new EmailNotification();
    }

    @Bean("Sms")
    public NotificationService smsNotificationService(){
        return new SmsNotification();
    }

    @Bean
    public ExpenseManager expenseManager(@Qualifier("Upi") PaymentService paymentService,
                                         @Qualifier("Email") NotificationService notificationService){
        ExpenseManager manager = new ExpenseManager();
        manager.paymentService = paymentService;
        manager.notificationService = notificationService;
        return manager;
    }
}