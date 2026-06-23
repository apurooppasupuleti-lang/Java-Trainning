package Payment;

import org.springframework.stereotype.Component;

@Component("Sms")
public class SmsNotification implements NotificationService {
    @Override
    public void sendNotification(String message) {
        System.out.println("Sending SMS Notification: " + message);
    }
}
