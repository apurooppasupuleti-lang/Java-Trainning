public class Person implements Comparable<Person> {

    int id;
    String name;
    int age;

    public Person(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Person p) {
        return this.id - p.id; // Sort by ID
    }

    @Override
    public String toString() {
        return "Person [id=" + id +
               ", name=" + name +
               ", age=" + age + "]";
    }
}