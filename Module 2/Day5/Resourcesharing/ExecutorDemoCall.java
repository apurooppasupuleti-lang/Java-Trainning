package executor;

import java.util.concurrent.*;
public class ExecutorDemoCall {
   public static void main(String[] args) {
       ExecutorService executor =
               Executors.newSingleThreadExecutor();

           Future<Integer> futureVal =executor.submit(new MyCallable());
       try {
           int value = futureVal.get(15, TimeUnit.SECONDS);
           System.out.println("Value: "+value);
       } catch (InterruptedException e) {
           throw new RuntimeException(e);
       } catch (ExecutionException e) {
           throw new RuntimeException(e);
       } catch (TimeoutException e) {
           throw new RuntimeException(e);
       }

       executor.shutdown();
   }
}