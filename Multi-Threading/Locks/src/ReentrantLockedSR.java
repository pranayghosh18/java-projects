import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockedSR {
    Queue<Integer> buffer;
    int capacity;
    ReentrantLock reentrantLock;
    Condition conditionProducer, conditionConsumer;
    Random randObj;

    ReentrantLockedSR(int capacity, ArrayDeque<Integer> buffer, ReentrantLock reentrantLock){
        randObj = new Random();
        this.capacity = capacity;
        this.buffer = buffer;
        this.reentrantLock = reentrantLock;
        conditionProducer = reentrantLock.newCondition();
        conditionConsumer = reentrantLock.newCondition();
    }

    // produce - logic
    public void produce (int producerSleepTime){
        try{
            reentrantLock.lock();
            while (buffer.size() == capacity) conditionProducer.await();

            // System.out.println("critical section at produce code");
            int item = randObj.nextInt();
            System.out.println("inserting item: " + item);
            buffer.add(item);
            conditionConsumer.signal();
            // System.out.println("sleeping " + producerSleepTime + " seconds" );
            Thread.sleep(producerSleepTime*1000);
            // System.out.println("@TRY -> is cur thread holding the lock ? : " + Thread.holdsLock(rentrantLock));
        }
        catch(Exception e){
            // System.out.println("exception and produce method " + e.toString());
        }
        finally{
            // System.out.println("produce method releasing critical section");
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
            reentrantLock.lock();
            while (buffer.size() == 0) conditionConsumer.await();
            // consuming
            // System.out.println("consumer ode critical section");
            int item = buffer.poll();
            System.out.println("fething item: " + item);

            conditionProducer.signal();
            // System.out.println("sleeping " + consumerSleepTime + " seconds" );
            Thread.sleep(consumerSleepTime*1000);
        }
        catch(Exception e){
            // System.out.println("exception and produce method " + e.toString());
        }
        finally{
            // System.out.println("produce method releasing critical section");
            reentrantLock.unlock();
        }
    }
}
