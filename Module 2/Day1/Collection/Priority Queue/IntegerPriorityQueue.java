import java.util.PriorityQueue;

public class IntegerPriorityQueue {

    public static void main(String[] args) {

        PriorityQueue<Integer> numbers = new PriorityQueue<>();

        numbers.add(30);
        numbers.add(10);
        numbers.add(20);
        numbers.add(40);

        System.out.println("Integer PriorityQueue:");

        while (!numbers.isEmpty()) {
            System.out.println(numbers.poll());
        }
    }
}