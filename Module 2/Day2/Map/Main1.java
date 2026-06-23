import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Main1 {
    public static void main(String[] args){
   // Map<String,String> map = new HashMap<>();
    Map<String,String> map = new TreeMap<>(Collections.reverseOrder());
    map.put("fname","Dhoni");
    map.put("lname","MS");
    map.put("Team","CSK");
    map.put("Position","Finisher");
    map.put("fname","Giva");
    System.out.println(map);
    System.out.println(map.get("fname"));
    System.out.println(map.get("lname"));
    System.out.println(map.get("Team"));
    System.out.println(map.get("Position"));

    System.out.println("------------------------------");
    map.keySet().forEach(System.out::println);
    System.out.println("------------------------------");
    map.values().forEach(System.out::println);
    System.out.println("------------------------------");
    for(Map.Entry<String,String> entry: map.entrySet()){
        System.out.println(entry.getKey()+" : "+entry.getValue());
    }
    System.out.println("------------------------------");
    map.entrySet().forEach(System.out::println);
    System.out.println("------------------------------");
    map.keySet().forEach(System.out::println);
    }
}

