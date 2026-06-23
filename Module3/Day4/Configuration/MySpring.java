package Payment;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "Payment")
public class MySpring {
    // All PaymentService and NotificationService implementations are now
    // discovered automatically via @ComponentScan, using the bean names
    // given in their @Component("...") annotations (CreditCard, DebitCard,
    // Upi, Email, Sms). No @Bean factory methods needed here anymore.
}
