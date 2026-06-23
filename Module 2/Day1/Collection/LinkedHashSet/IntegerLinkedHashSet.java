import java.util.LinkedHashSet;

public class IntegerLinkedHashSet {

    public static void main(String[] args) {

        LinkedHashSet<Integer> numbers = new LinkedHashSet<>();

        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        numbers.add(10); // Duplicate

        System.out.println("Integer LinkedHashSet:");

        for (Integer n : numbers) {
            System.out.println(n);
        }
    }
}