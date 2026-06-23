import java.util.TreeSet;

class Person implements Comparable<Person> {

    int id;
    String name;
    int age;

    Person(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Person p) {
        return this.id - p.id;
    }

    @Override
    public String toString() {
        return "Person [id=" + id +
               ", name=" + name +
               ", age=" + age + "]";
    }
}

public class TreeSetDemo {

    public static void main(String[] args) {

        // Integer TreeSet
        TreeSet<Integer> numbers = new TreeSet<>();

        numbers.add(30);
        numbers.add(10);
        numbers.add(20);
        numbers.add(10);

        System.out.println("Integer TreeSet:");
        System.out.println(numbers);

        // String TreeSet
        TreeSet<String> names = new TreeSet<>();

        names.add("Ravi");
        names.add("Kiran");
        names.add("Asha");
        names.add("Ravi");

        System.out.println("\nString TreeSet:");
        System.out.println(names);

        // Person TreeSet
        TreeSet<Person> persons = new TreeSet<>();

        persons.add(new Person(103, "Asha", 21));
        persons.add(new Person(101, "Ravi", 22));
        persons.add(new Person(102, "Kiran", 24));

        System.out.println("\nPerson TreeSet:");

        for (Person p : persons) {
            System.out.println(p);
        }
    }
}