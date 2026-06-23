import java.util.TreeSet;

public class IntegerTreeSet {

    public static void main(String[] args) {

        TreeSet<Integer> numbers = new TreeSet<>();

        numbers.add(30);
        numbers.add(10);
        numbers.add(20);
        numbers.add(10); // Duplicate

        System.out.println("Integer TreeSet:");

        for (Integer n : numbers) {
            System.out.println(n);
        }
    }
}