import java.util.LinkedHashSet;

public class PersonLinkedHashSet {

    public static void main(String[] args) {

        LinkedHashSet<Person> persons = new LinkedHashSet<>();

        persons.add(new Person(101, "Ravi", 22));
        persons.add(new Person(102, "Kiran", 24));
        persons.add(new Person(103, "Asha", 21));

        System.out.println("Person LinkedHashSet:");

        for (Person p : persons) {
            System.out.println(p);
        }
    }
}