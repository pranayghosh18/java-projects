package PrintInorder_1114.Semaphore;

import java.util.concurrent.Semaphore;

class Foo {
    Semaphore s1, s2, s3;

    public Foo() {
        s1 = new Semaphore(1); // as first will execute first
        s2 = new Semaphore(0);
        s3 = new Semaphore(0);
    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        s1.acquire();
        printFirst.run();
        s2.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        // printSecond.run() outputs "second". Do not change or remove this line.
        s2.acquire();
        printSecond.run();
        s3.release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        // printThird.run() outputs "thirst". Do not change or remove this line.
        s3.acquire();
        printThird.run();
    }
}