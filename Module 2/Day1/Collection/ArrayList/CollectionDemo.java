import java.util.ArrayList;

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
        return "Person [id=" + id + ", name=" + name + ", age=" + age + "]";
    }
}

public class CollectionDemo {

    public static void main(String[] args) {

        // Integer ArrayList
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);

        System.out.println("Integer List:");
        System.out.println(numbers);

        // String ArrayList
        ArrayList<String> names = new ArrayList<>();
        names.add("Ravi");
        names.add("Kiran");
        names.add("Asha");

        System.out.println("\nString List:");
        System.out.println(names);

        // Person ArrayList
        ArrayList<Person> persons = new ArrayList<>();
        persons.add(new Person(101, "Ravi", 22));
        persons.add(new Person(102, "Kiran", 24));
        persons.add(new Person(103, "Asha", 21));

        System.out.println("\nPerson List:");
        for (Person p : persons) {
            System.out.println(p);
        }
    }
}