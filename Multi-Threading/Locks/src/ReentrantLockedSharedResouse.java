import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockedSharedResouse {
    Queue<Integer> buffer;
    int capacity;
    ReentrantLock reentrantLock;
    Condition condition;
    Random randObj;

    ReentrantLockedSharedResouse(int capacity, ArrayDeque<Integer> buffer, ReentrantLock reentrantLock){
        randObj = new Random();
        this.capacity = capacity;
        this.buffer = buffer;
        this.reentrantLock = reentrantLock;
        condition = reentrantLock.newCondition();
    }

    // produce - logic
    public void produce (int producerSleepTime) throws Exception{
        
        try{
            while (buffer.size() == capacity) condition.await();

            // boolean locked = reentrantLock.tryLock();
            while (reentrantLock.tryLock() == false) {
                System.out.println("produce waiting to get lock");
            }
            System.out.println("critical section at produce code");
            int item = randObj.nextInt();
            System.out.println("inserting item: " + item);
            buffer.add(item);
            System.out.println("sleeping " + producerSleepTime + " seconds" );
            Thread.sleep(producerSleepTime*1000);
            // System.out.println("@TRY -> is cur thread holding the lock ? : " + Thread.holdsLock(rentrantLock));
        }
        catch(Exception e){
            System.out.println("exception and produce method " + e.toString());
        }
        finally{
            System.out.println("produce method releasing critical section");
            // System.out.println("is cur thread holding the lock ? : " + Thread.holdsLock(rentrantLock));
            // while (!Thread.holdsLock(rentrantLock)) {
            //     System.out.println("looping");
            //     Thread.sleep(10000);
            // } 
            reentrantLock.unlock();
        }
    }
    // consume - logic
    public void consume(int consumerSleepTime){
        
        try{
            while (buffer.size() == 0) {
                System.out.println("buffer size 0, so consumer waiting");
                // condition.await();
            }

            while (reentrantLock.tryLock() == false) {
                System.out.println("consume waiting to get lock");
            }

            // reentrantLock.lock();
            System.out.println("consumer code's critical section");
            int item = buffer.poll();
            System.out.println("consuming item: " + item);
            System.out.println("sleeping " + consumerSleepTime + " seconds" );
            Thread.sleep(consumerSleepTime*1000);
        }
        catch(Exception e){
            System.out.println("exception at consume method " + e.toString());
        }
        finally{
            System.out.println("critical section of consume method releasing lock");
            reentrantLock.unlock();
        }
    }
}
