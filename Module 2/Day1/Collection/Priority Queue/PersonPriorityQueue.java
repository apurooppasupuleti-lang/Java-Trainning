import java.util.PriorityQueue;

public class PersonPriorityQueue {

    public static void main(String[] args) {

        PriorityQueue<Person> persons = new PriorityQueue<>();

        persons.add(new Person(103, "Asha", 21));
        persons.add(new Person(101, "Ravi", 22));
        persons.add(new Person(102, "Kiran", 24));

        System.out.println("Person PriorityQueue:");

        while (!persons.isEmpty()) {
            System.out.println(persons.poll());
        }
    }
}