package Iteration;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class PersonMain {

    public static void main(String[] args) {

        List<PersonMain> list = new LinkedList<>();

        list.add(new PersonMain(101, "Ravi", 22));
        list.add(new PersonMain(102, "Kiran", 24));
        list.add(new PersonMain(103, "Asha", 21));

        System.out.println("Using For-Each Loop");
        System.out.println("-------------------");

        for (PersonMain p : list) {
            System.out.println(p);
        }

        Iterator<PersonMain> itr = list.iterator();

        System.out.println("\nIterator Class:");
        System.out.println(itr.getClass().getName());

        System.out.println("\nUsing Iterator");
        System.out.println("--------------");

        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
    }
}