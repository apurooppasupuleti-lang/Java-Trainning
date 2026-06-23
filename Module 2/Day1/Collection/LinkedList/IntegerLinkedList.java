import java.util.LinkedList;

public class IntegerLinkedList {

    public static void main(String[] args) {

        LinkedList<Integer> numbers = new LinkedList<>();

        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        numbers.add(40);

        System.out.println("Integer LinkedList:");
        System.out.println(numbers);

        for(Integer n : numbers) {
            System.out.println(n);
        }
    }
}