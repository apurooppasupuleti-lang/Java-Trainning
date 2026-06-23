package MultiThreading;

public class Main3 {
    public static void main(String[] args) {
        
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName());
            }).start();
        

        System.out.println("Exiting Main Thread");
    }
}
}