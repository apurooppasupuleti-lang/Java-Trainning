import java.util.LinkedList;

public class StringLinkedList {

    public static void main(String[] args) {

        LinkedList<String> names = new LinkedList<>();

        names.add("Ravi");
        names.add("Kiran");
        names.add("Asha");
        names.add("Priya");

        System.out.println("String LinkedList:");
        System.out.println(names);

        for(String name : names) {
            System.out.println(name);
        }
    }
}