import java.util.Scanner;

public class MainD {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExpenseManager manager = new ExpenseManager();

        System.out.println("Choose Payment Method:");
        System.out.println("1. Credit Card  - type 'credit'");
        System.out.println("2. Debit Card   - type 'debit'");
        System.out.println("3. UPI          - type 'upi'");
        System.out.print("Enter choice: ");
        String choice = scanner.nextLine();

        PaymentService service = PaymentFactory.getPaymentService(choice);

        if (service == null) {
            System.out.println("Invalid payment method!");
            return;
        }

        manager.setPaymentService(service);

        System.out.print("Enter Electricity Bill amount: ");
        double electricity = scanner.nextDouble();
        manager.payElectricityBill(electricity);

        System.out.print("Enter Water Bill amount: ");
        double water = scanner.nextDouble();
        manager.payWaterBill(water);

        System.out.print("Enter Gas Bill amount: ");
        double gas = scanner.nextDouble();
        manager.payGasBill(gas);

        scanner.close();
    }
}