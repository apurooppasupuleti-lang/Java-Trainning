@FunctionalInterface
interface Greeting {
    void message();
}

public class Main1 {
    public static void main(String[] args) {

        Greeting morning = () -> System.out.println("Good Morning!");
        Greeting night = () -> System.out.println("Good Night!");

        morning.message();
        night.message();
    }
}