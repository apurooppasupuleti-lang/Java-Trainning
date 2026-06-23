import java.util.PriorityQueue;

public class StringPriorityQueue {

    public static void main(String[] args) {

        PriorityQueue<String> names = new PriorityQueue<>();

        names.add("Ravi");
        names.add("Kiran");
        names.add("Asha");
        names.add("Priya");

        System.out.println("String PriorityQueue:");

        while (!names.isEmpty()) {
            System.out.println(names.poll());
        }
    }
}