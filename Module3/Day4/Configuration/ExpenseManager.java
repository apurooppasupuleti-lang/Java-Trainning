package Payment;

public class ExpenseManager {
    public PaymentService paymentService;
    public NotificationService notificationService;

    public void payHouseRent() {
        paymentService.pay(14000, "house rent");
        notificationService.sendNotification("Paying house rent is done");
    }

    public void payElectricityBill() {
        paymentService.pay(1000, "electricity bill");
        notificationService.sendNotification("Paying electricity bill is done");
    }

    public void payWaterBill() {
        paymentService.pay(140, "water bill");
        notificationService.sendNotification("Paying water bill is done");
    }
}
