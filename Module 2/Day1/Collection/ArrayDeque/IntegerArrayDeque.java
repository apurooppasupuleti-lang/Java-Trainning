import java.util.ArrayDeque;

public class IntegerArrayDeque {

    public static void main(String[] args) {

        ArrayDeque<Integer> numbers = new ArrayDeque<>();

        numbers.addFirst(10);
        numbers.addLast(20);
        numbers.addFirst(5);
        numbers.addLast(30);

        System.out.println("Integer ArrayDeque:");
        System.out.println(numbers);

        System.out.println("First Element: " + numbers.getFirst());
        System.out.println("Last Element: " + numbers.getLast());
    }
}