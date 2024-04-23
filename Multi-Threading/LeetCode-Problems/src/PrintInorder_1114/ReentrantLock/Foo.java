package PrintInorder_1114.ReentrantLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class Foo {
    ReentrantLock lock;
    Condition c2, c3;
    int turn;

    public Foo() {
        lock = new ReentrantLock();
        c2 = lock.newCondition();
        c3 = lock.newCondition();
        turn = 0;
    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        lock.lock();
        printFirst.run();
        turn++;
        c2.signal();
        lock.unlock();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        // printSecond.run() outputs "second". Do not change or remove this line.
        lock.lock();
        while (turn < 1) {
            c2.await();
        }
        printSecond.run();
        turn++;
        c3.signal();
        lock.unlock();
    }

    public void third(Runnable printThird) throws InterruptedException {
        // printThird.run() outputs "thirst". Do not change or remove this line.
        lock.lock();
        while (turn < 2) {
            c3.await();
        }
        printThird.run();
        lock.unlock();
    }
}