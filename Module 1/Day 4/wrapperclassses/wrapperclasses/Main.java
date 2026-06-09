package intro;

public class Main {
    public static void main(String[] args) {
        int value = 10;
            Integer i = value; // autoboxing
            System.out.println(i);

            int unboxed = i.intValue();
            System.out.println(unboxed);
       
        
    }
}