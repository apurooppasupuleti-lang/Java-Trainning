import java.util.TreeSet;


public class PersonTreeSet {

    public static void main(String[] args) {

        TreeSet<Person> persons = new TreeSet<>();

        persons.add(new Person(103, "Asha", 21));
        persons.add(new Person(101, "Ravi", 22));
        persons.add(new Person(102, "Kiran", 24));

        System.out.println("Person TreeSet:");

        for (Person p : persons) {
            System.out.println(p);
        }
    }
}