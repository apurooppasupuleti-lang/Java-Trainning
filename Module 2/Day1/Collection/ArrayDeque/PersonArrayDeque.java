import java.util.ArrayDeque;

public class PersonArrayDeque {

    public static void main(String[] args) {

        ArrayDeque<Person> persons = new ArrayDeque<>();

        persons.addFirst(new Person(101, "Ravi", 22));
        persons.addLast(new Person(102, "Kiran", 24));
        persons.addFirst(new Person(103, "Asha", 21));

        System.out.println("Person ArrayDeque:");

        for (Person p : persons) {
            System.out.println(p);
        }

        System.out.println("\nFirst Person:");
        System.out.println(persons.getFirst());

        System.out.println("\nLast Person:");
        System.out.println(persons.getLast());
    }
}