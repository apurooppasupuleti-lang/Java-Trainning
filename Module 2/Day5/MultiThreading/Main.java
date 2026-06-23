package MultiThreading;

public class Main {
    public static void main(String[] args) {
        Thread t1 = new MyThread("Apuroop",500);
        Thread t2 = new MyThread("Pasupuleti",1000);
        t1.start();
        t2.start();
        // Thread joining
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Active Threads: " + Thread.activeCount());
        for(int i=0;i<100;i++){
            System.out.println(i+" "+Thread.currentThread().getName());
        }
    }
}
