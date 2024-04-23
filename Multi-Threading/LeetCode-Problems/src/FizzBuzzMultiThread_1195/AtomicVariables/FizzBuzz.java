package FizzBuzzMultiThread_1195.AtomicVariables;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;

class FizzBuzz {
    private int n;
    private AtomicInteger turn;

    public FizzBuzz(int n) {
        this.n = n;
        this.turn = new AtomicInteger(1);
        // this.turn.set(1);
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        while (turn.get() <= n) {
            while (turn.get()<=n && (turn.get()%3 != 0 || turn.get()%15 == 0)) {
                System.out.println("waiting at FIZZ, turn val :: " + turn.get());
            }
            if(turn.get()<=n) printFizz.run();
            turn.incrementAndGet();
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        while (turn.get() <= n) {
            while (turn.get() <= n && (turn.get()%5 != 0 || turn.get()%15 == 0)) {
                System.out.println("waiting at BUZZ, turn val :: " + turn.get());
            }
            if(turn.get()<=n) printBuzz.run();
            turn.incrementAndGet();
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (turn.get() <= n) {
            while (turn.get()<=n && turn.get()%15 != 0) {
                System.out.println("waiting at FIZZBUZZ, turn val :: " + turn.get());
            }
            if(turn.get()<=n) printFizzBuzz.run();
            turn.incrementAndGet();
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        while (turn.get() <= n) {
            while (turn.get()%3 == 0 || turn.get()%5 == 0 || turn.get()%15 == 0) {
                System.out.println("waiting at NUMBER, turn val :: " + turn.get());
            }
            if(turn.get()<=n) printNumber.accept(turn.get());
            turn.incrementAndGet();
        }
    }
}
