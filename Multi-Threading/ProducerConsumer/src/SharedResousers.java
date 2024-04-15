import java.util.Queue;
import java.util.Random;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class SharedResousers {
    Queue<Integer> buffer;
    int capacity;
    Random randObj;

    public SharedResousers(int capacity) {
        // this.buffer = new PriorityQueue<Integer>();
        this.buffer = new LinkedList<Integer>();
        this.capacity = capacity;
        this.randObj = new Random();
    }
    
    public synchronized void produce() throws Exception{
        // System.out.println("producer call, buffer size : " + this.buffer.size());
        while (this.buffer.size() == this.capacity) {
            wait();
        }

        int val = randObj.nextInt(1000);
        System.out.println("inserting element : " + val);
        this.buffer.add(val);
        notifyAll();
    }

    public synchronized void consume() throws Exception{
        while (buffer.isEmpty()) {
            wait();
        }
        int front = buffer.poll();
        System.out.println("consuming element : " + front);
        notifyAll();
    }

}
