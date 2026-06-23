package Iteration;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Main1 {

    public static void main(String[] args) {

        List<Integer> list = new LinkedList<>();

        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);

        System.out.println("---------------");

        for (Integer num : list) {
            System.out.println(num);
        }

        Iterator<Integer> itr = list.iterator();

        System.out.println("\nIterator Class:");
        System.out.println(itr.getClass().getName());

        System.out.println("\nUsing Iterator:");

        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
    }
}