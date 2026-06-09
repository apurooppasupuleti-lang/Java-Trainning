package wrapperclasses;

public class Main1 {
    public static void main(String[] args) {
        boolean value = true;

        Boolean b = value;
        System.out.println(b);

        boolean unboxed = b.booleanValue();
        System.out.println(unboxed);
    }
} 
        