package wrapperclasses;

public class MainS {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("Hello");
        System.out.println(sb);
        sb.append("World");
        System.out.println(sb);
        sb.insert(0,"Hi");
        System.out.println(sb);
        sb.delete(0,3);
        System.out.println(sb);
        sb.replace(2,4,"GoodBye" );
        System.out.println(sb);
    }
}