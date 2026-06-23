import java.util.TreeSet;

public class StringTreeSet {

    public static void main(String[] args) {

        TreeSet<String> names = new TreeSet<>();

        names.add("Ravi");
        names.add("Kiran");
        names.add("Asha");
        names.add("Ravi"); // Duplicate

        System.out.println("String TreeSet:");

        for (String name : names) {
            System.out.println(name);
        }
    }
}