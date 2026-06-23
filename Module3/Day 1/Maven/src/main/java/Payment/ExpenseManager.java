package Payment;

public class ExpenseManager {
    public PaymentService paymentService;
    public NotificationService notificationService;

    public void payHouseRent() {
        paymentService.pay(14000);
        notificationService.sendNotification("Paying through credit card for house rent is done");
    }

    public void ElectricityBill() {
        paymentService.pay(1000);
        notificationService.sendNotification("Paying electricity bill");
    }

    public void WaterBill() {
        paymentService.pay(140);
        notificationService.sendNotification("Paying water bill");
    }
}