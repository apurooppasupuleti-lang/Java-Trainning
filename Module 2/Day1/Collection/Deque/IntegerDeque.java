import java.util.ArrayDeque;
import java.util.Deque;

public class IntegerDeque {

    public static void main(String[] args) {

        Deque<Integer> numbers = new ArrayDeque<>();

        numbers.addFirst(10);
        numbers.addLast(20);
        numbers.addFirst(5);
        numbers.addLast(30);

        System.out.println("Integer Deque:");
        System.out.println(numbers);

        System.out.println("First Element: " + numbers.getFirst());
        System.out.println("Last Element: " + numbers.getLast());
    }
}