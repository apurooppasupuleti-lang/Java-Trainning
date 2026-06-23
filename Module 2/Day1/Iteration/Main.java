package Iteration;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
public class Main {
    public static void main (String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Sachin");
        list.add("Saurav");
        list.add("Rahul");
        list.add("Yuvraj");
        System.out.println("---------------");
        for(String str:list) {
            System.out.println(str);
        }
        Iterator<String> itr = list.iterator();
        System.out.println(itr.getClass().getName());
        System.out.println();
        while(itr.hasNext()) {
            System.out.println(itr.next());
        }
    }
}