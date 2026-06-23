public class ExpenseManager {
    private PaymentService paymentService;
    private NotificationService notificationService;

    public ExpenseManager() {
        this.paymentService = new DebitCard();
        this.notificationService = new EmailNotification();
    }

    // Constructor injection
    public ExpenseManager(PaymentService paymentService, NotificationService notificationService) {
        this.paymentService = paymentService;
        this.notificationService = notificationService;
    }

    // Setter injection
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void payElectricityBill(double amount) {
        System.out.println("Paying electricity bill of " + amount);
        paymentService.pay(amount);
        notificationService.sendNotification("Electricity bill of " + amount + " paid");
        System.out.println("Electricity bill paid");
    }

    public void payWaterBill(double amount) {
        System.out.println("Paying water bill of " + amount);
        paymentService.pay(amount);
        notificationService.sendNotification("Water bill of " + amount + " paid");
        System.out.println("Water bill paid");
    }

    public void payGasBill(double amount) {
        System.out.println("Paying gas bill of " + amount);
        paymentService.pay(amount);
        notificationService.sendNotification("Gas bill of " + amount + " paid");
        System.out.println("Gas bill paid");
    }
}