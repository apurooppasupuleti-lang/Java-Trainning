import java.util.HashSet;

public class StringHashSet {

    public static void main(String[] args) {

        HashSet<String> names = new HashSet<>();

        names.add("Ravi");
        names.add("Kiran");
        names.add("Asha");
        names.add("Ravi"); // Duplicate

        System.out.println("String HashSet:");

        for(String name : names) {
            System.out.println(name);
        }
    }
}