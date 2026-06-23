import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class PersonMain {

    public static void main(String[] args) {

        List<Person> list = new LinkedList<>();

        list.add(new Person(101, "Ravi", 22));
        list.add(new Person(102, "Kiran", 24));
        list.add(new Person(103, "Asha", 21));

        System.out.println("Using For Each");

        for (Person p : list) {
            System.out.println(p);
        }

        System.out.println("\nUsing Iterator");

        Iterator<Person> itr = list.iterator();

        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
    }
}