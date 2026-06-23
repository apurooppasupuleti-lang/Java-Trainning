import java.util.ArrayDeque;
import java.util.Deque;

public class StringDeque {

    public static void main(String[] args) {

        Deque<String> names = new ArrayDeque<>();

        names.addFirst("Ravi");
        names.addLast("Kiran");
        names.addFirst("Asha");
        names.addLast("Priya");

        System.out.println("String Deque:");
        System.out.println(names);

        System.out.println("First Element: " + names.getFirst());
        System.out.println("Last Element: " + names.getLast());
    }
}