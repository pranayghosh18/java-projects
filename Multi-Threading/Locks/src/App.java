import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

public class App {
    public static void main(String[] args) throws Exception {
        ArrayDeque<Integer> buffer = new ArrayDeque<Integer>();
        ReentrantLock reentrantLock1 = new ReentrantLock();
        ReentrantLock reentrantLock2 = new ReentrantLock();
        // ReentrantLockedSharedResouse resouse1 = new ReentrantLockedSharedResouse(5, buffer, reentrantLock1);
        // ReentrantLockedSharedResouse resouse2 = new ReentrantLockedSharedResouse(5, buffer, reentrantLock2);
        // // fast consumer slow producer
        // Thread producerThread = new Thread(()->{
        //     try{
        //         for(int i=0; i<5; i++) resouse1.produce(1);
        //     }catch(Exception e){
        //         System.out.println("Exception " + e.toString());
        //     }
        // });

        // Thread consumerThread = new Thread(()->{
        //     for(int i=0; i<10; i++) resouse1.consume(2); 
        // });

        // producerThread.start();
        // consumerThread.start();

        // producerThread.join();
        // consumerThread.join();
        // System.out.println("main is done");

        ReentrantLockedSR sharedRes = new ReentrantLockedSR(2, buffer, reentrantLock2);
        Thread producerTask = new Thread(()->{
            for(int i=0; i<5; i++) sharedRes.produce(4);
        });

        Thread consumerTask = new Thread(()->{
            for(int i=0; i<5; i++) sharedRes.consume(2);
        });

        producerTask.start();
        consumerTask.start();

        producerTask.join();
        consumerTask.join();
    }
}
