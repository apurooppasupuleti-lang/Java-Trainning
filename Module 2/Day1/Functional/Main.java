public class Main {

    public static void main(String[] args) {

        Greet g1 = new Morning();
        Greet g2 = new Night();

        g1.message();
        g2.message();
    }
}