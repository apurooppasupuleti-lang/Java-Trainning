import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
public  class Interconversion {
    public static void main(String[] args) {
    List<Integer> list = new ArrayList<>();
        list.add(30);
        list.add(40);
        list.add(50);
        list.add(10);
        list.add(20);
        list.add(30);
        System.out.println(list);
        System.out.println("------------------------------");
        Set<Integer> set = new LinkedHashSet<>(list);
        System.out.println(set);
        System.out.println("------------------------------");
        list = new ArrayList<>(set);
        System.out.println(list);
        System.out.println("------------------------------");
        System.out.println(list instanceof Set);
        System.out.println(list instanceof List);
    }

}