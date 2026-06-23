import java.util.HashSet;

public class PersonHashSet {

    public static void main(String[] args) {

        HashSet<Person> persons = new HashSet<>();

        persons.add(new Person(101, "Ravi", 22));
        persons.add(new Person(102, "Kiran", 24));
        persons.add(new Person(103, "Asha", 21));

        System.out.println("Person HashSet:");

        for(Person p : persons) {
            System.out.println(p);
        }
    }
}