public class PaymentFactory {
    private static CreditCard creditCard = new CreditCard();
    private static DebitCard debitCard = new DebitCard();
    private static UPI upi = new UPI();

    public static PaymentService getPaymentService(String paymentType) {
        if (paymentType.equalsIgnoreCase("credit")) {
            return creditCard;
        } else if (paymentType.equalsIgnoreCase("debit")) {
            return debitCard;
        } else if (paymentType.equalsIgnoreCase("upi")) {
            return upi;
        }
        return null;
    }
}