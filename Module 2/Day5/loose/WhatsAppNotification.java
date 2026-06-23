public class WhatsAppNotification implements NotificationService {
    @Override
    public void sendNotification(String message) {
        System.out.println("Sending WhatsApp Notification: " + message);
    }
}