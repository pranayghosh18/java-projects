package PrinrZeroEvenOdd_1116.Semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;

class ZeroEvenOdd {
    private int n, turn;
    AtomicInteger autoTurn;
    Semaphore zero, even, odd;
    
    public ZeroEvenOdd(int n) {
        this.n = n;
        this.turn = 1;
        autoTurn = new AtomicInteger(1);
        zero = new Semaphore(1);
        even = new Semaphore(0);
        odd = new Semaphore(0);
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        while (autoTurn.get() <= n) {
            zero.acquire();
            printNumber.accept(0);
            if(turn%2 == 1) odd.release();
            else{
                even.release();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        while (autoTurn.get() <= n) {
            even.acquire();
            printNumber.accept(turn);
            // turn++;
            autoTurn.incrementAndGet();
            zero.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        while (autoTurn.get() <= n) {
            odd.acquire();
            printNumber.accept(turn);
            // turn++;
            autoTurn.incrementAndGet();
            zero.release();
        }
    }
}