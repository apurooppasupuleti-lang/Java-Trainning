package MultiThreading;

public class MyRunnable implements Runnalbe {

    @Override 
    public void run(){
        for(int i=1;i<=100;i++){
            System.out.println(i+" "+Thread.currentThread().getName());
        }

    }
    
}