package FizzBuzzMultiThread_1195.SynchroziedMonitorLock;

import java.util.function.IntConsumer;

class FizzBuzz {
    private int n;
    private int taken;

    public FizzBuzz(int n) {
        this.n = n;
        this.taken = 1;
    }

    // printFizz.run() outputs "fizz".
    public synchronized void fizz(Runnable printFizz) throws InterruptedException {
        while (taken <= n) {
            if(taken%3 == 0 && taken%15 != 0) {
                printFizz.run();
                taken++;
                notifyAll();
            }
            else{
                wait();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public synchronized void buzz(Runnable printBuzz) throws InterruptedException {
        while (taken <= n) {
            if(taken%5 == 0 && taken%15 != 0) {
                printBuzz.run();
                taken++;
                notifyAll();
            }
            else{
                wait();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public synchronized void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (taken <= n) {
            if(taken%15 == 0) {
                printFizzBuzz.run();
                taken++;
                notifyAll();
            }
            else{
                wait();
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public synchronized void number(IntConsumer printNumber) throws InterruptedException {
        // while (taken <= n) {
        //     if(taken%3 == 0 && taken%5 == 0) {
        //         wait();
        //     }
        //     else{
        //         printNumber.accept(taken);
        //         taken++;
        //         notifyAll();
        //     }
        // }
        while (taken <= n) {
            if(taken%3 != 0 && taken%5 != 0) {
                printNumber.accept(taken);
                notifyAll();
            }
            else{
                wait();
            }
        }
    }
}
