import java.util.ArrayDeque;

public class StringArrayDeque {

    public static void main(String[] args) {

        ArrayDeque<String> names = new ArrayDeque<>();

        names.addFirst("Ravi");
        names.addLast("Kiran");
        names.addFirst("Asha");
        names.addLast("Priya");

        System.out.println("String ArrayDeque:");
        System.out.println(names);

        System.out.println("First Element: " + names.getFirst());
        System.out.println("Last Element: " + names.getLast());
    }
}