import java.util.LinkedList;

public class PersonLinkedList {

    public static void main(String[] args) {

        LinkedList<PersonMain> persons = new LinkedList<>();

        persons.add(new PersonMain(101, "Ravi", 22));
        persons.add(new PersonMain(102, "Kiran", 24));
        persons.add(new PersonMain(103, "Asha", 21));

        System.out.println("Person LinkedList:");

        for(PersonMain p : persons) {
            System.out.println(p);
        }
    }
}