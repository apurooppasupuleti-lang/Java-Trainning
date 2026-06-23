import java.util.InputMismatchException;
import java.util.LinkedList;

class Person {
    int id;
    String name;
    int age;

    Person(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person [id=" + id +
               ", name=" + name +
               ", age=" + age + "]";
    }
}

public class LinkedList Main {

    public static void main(String[] args) {

        // Integer LinkedList
        LinkedList Main<Integer> numbers = new LinkedList<>();

        numbers.add(10);
        numbers.add(20);
        numbers.add(30);

        System.out.println("Integer LinkedList:");
        System.out.println(numbers);

        // String LinkedList
        LinkedList<String> names = new LinkedList<>();

        names.add("Ravi");
        names.add("Kiran");
        names.add("Asha");

        System.out.println("\nString LinkedList:");
        System.out.println(names);

        // Person LinkedList
        LinkedList<Person> persons = new LinkedList<>();

        persons.add(new Person(101, "Ravi", 22));
        persons.add(new Person(102, "Kiran", 24));
        persons.add(new Person(103, "Asha", 21));

        System.out.println("\nPerson LinkedList:");

        for (Person p : persons) {
            System.out.println(p);
        }
    }
}