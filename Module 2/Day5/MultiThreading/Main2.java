public class Main2 {
    public static void main(String[] args) {
        Runnable r = () -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(i + " " + Thread.currentThread().getName());
            }
        };

        Thread t1 = new Thread(r, "Apuroop");
        Thread t2 = new Thread(r, "Pasupuleti");
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();

            System.out.println("Active threads:" + Thread.activeCount());
            for (int i = 0; i < 100; i++) {
                System.out.println(i + " " + Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}