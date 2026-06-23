import java.util.ArrayList;

public class StringList {
    public static void main(String[] args) {

        ArrayList<String> names = new ArrayList<>();

        names.add("Ravi");
        names.add("Kiran");
        names.add("Asha");
        names.add("Priya");

        System.out.println("Names: " + names);

        for(String name : names) {
            System.out.println(name);
        }
    }
}