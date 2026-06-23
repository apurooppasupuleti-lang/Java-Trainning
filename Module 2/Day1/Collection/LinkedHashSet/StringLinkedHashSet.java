import java.util.LinkedHashSet;

public class StringLinkedHashSet {

    public static void main(String[] args) {

        LinkedHashSet<String> names = new LinkedHashSet<>();

        names.add("Ravi");
        names.add("Kiran");
        names.add("Asha");
        names.add("Ravi"); // Duplicate

        System.out.println("String LinkedHashSet:");

        for (String name : names) {
            System.out.println(name);
        }
    }
}