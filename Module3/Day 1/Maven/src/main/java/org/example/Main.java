package org.example;

import Payment.NotificationService;
import Payment.PaymentService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the amount to pay:");
        double amount = Double.parseDouble(scanner.nextLine().trim());

        System.out.println("Enter your choice: 1.CreditCard 2.DebitCard 3.UPI ,default is Upi");
        String paymentChoice = scanner.nextLine().trim();

        System.out.println("Enter your notification type : 1.email 2.sms ,default is email");
        String notificationChoice = scanner.nextLine().trim();

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("Payment");

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


        String notificationBeanName;
        switch (notificationChoice) {
            case "2":
                notificationBeanName = "Sms";
                break;
            case "1":
            default:
                notificationBeanName = "Email";
        }

        PaymentService paymentService = context.getBean(paymentBeanName, PaymentService.class);
        NotificationService notificationService = context.getBean(notificationBeanName, NotificationService.class);

        paymentService.pay(amount);
        notificationService.sendNotification("Payment of " + amount + " processed successfully");

        context.close();
    }
}