package Configuration.iml;

import Payment.ExpenseManager;
import Payment.NotificationService;
import Payment.PaymentService;
import Payment.UpiPayment;
import jdk.jfr.BooleanFlag;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
@ComponentScan(basePackages = "Payment")
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Main.class);

        System.out.println("Enter your choice: 1.CreditCard 2.DebitCard 3.UPI , default is Upi");
        String paymentChoice = scanner.nextLine().trim();

        String paymentBeanName;
        switch (paymentChoice) {
            case "1":
                paymentBeanName = "CreditCard";
                break;
            case "2":
                paymentBeanName = "DebitCard";
                break;
            case "3":
                paymentBeanName = "Upi";
                break;
            default:
                paymentBeanName = "Upi";
        }

        System.out.println("Enter your notification type : 1.email 2.sms , default is email");
        String notificationChoice = scanner.nextLine().trim();

        String notificationBeanName;
        switch (notificationChoice) {
            case "2":
                notificationBeanName = "Sms";
                break;
            case "1":
            default:
                notificationBeanName = "Email";
        }

        System.out.println("Enter the bill to pay: 1.House Rent 2.Water Bill 3.Electricity Bill");
        String billChoice = scanner.nextLine().trim();

        PaymentService paymentService = context.getBean(paymentBeanName, PaymentService.class);
        NotificationService notificationService = context.getBean(notificationBeanName, NotificationService.class);
        ExpenseManager manager = new ExpenseManager();
        manager.paymentService = paymentService;
        manager.notificationService = notificationService;

        switch (billChoice) {
            case "2":
                manager.payWaterBill();
                break;
            case "3":
                manager.payElectricityBill();
                break;
            case "1":
            default:
                manager.payHouseRent();
        }

        context.close();
    }
}