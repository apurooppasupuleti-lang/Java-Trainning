import java.util.HashSet;

public class IntegerHashSet {

    public static void main(String[] args) {

        HashSet<Integer> numbers = new HashSet<>();

        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        numbers.add(10); // Duplicate

        System.out.println("Integer HashSet:");

        for(Integer n : numbers) {
            System.out.println(n);
        }
    }
}