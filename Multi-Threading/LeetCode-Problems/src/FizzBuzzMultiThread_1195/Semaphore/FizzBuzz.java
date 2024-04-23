package FizzBuzzMultiThread_1195.Semaphore;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

class FizzBuzz {
    private int n;
    int turn;
    Semaphore fizz, buzz, fizzbuzz, number;

    public FizzBuzz(int n) {
        this.n = n;
        turn = 1;
        fizz = new Semaphore(0);
        buzz = new Semaphore(0);
        fizzbuzz = new Semaphore(0);
        number = new Semaphore(0);
        releaseLock(turn);
    }

    public void releaseLock(int turn){
        if(turn > n){
            fizz.release();
            buzz.release();
            fizzbuzz.release();
            number.release();
        }
        if(turn % 15 == 0) fizzbuzz.release();
        else if(turn % 5 == 0) buzz.release();
        else if(turn % 3 == 0) fizz.release();
        else{
            number.release();
        }
    }
    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        // int turn = 1;
        while(turn <= n){
            if(turn%3 == 0 && turn%5 != 0){
                fizz.acquire();
                printFizz.run();
                turn++;
                releaseLock(turn);
            }
            // turn++;
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        // int turn = 1;
        while(turn <= n){
            if(turn%5 == 0 && turn%3 != 0){
                buzz.acquire();
                printBuzz.run();
                turn++;
                releaseLock(turn);
            }
            // turn++;
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        // int turn = 1;
        while(turn <= n){
            if(turn%15 == 0){
                fizzbuzz.acquire();
                printFizzBuzz.run();
                turn++;
                releaseLock(turn);
            }
            // turn++;
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        // int turn = 1;
        while(turn <= n){
            if(turn%3 != 0 && turn%5 != 0){
                number.acquire();
                printNumber.accept(turn);
                turn++;
                releaseLock(turn);
            }
            // turn++;
        }
    }
}
