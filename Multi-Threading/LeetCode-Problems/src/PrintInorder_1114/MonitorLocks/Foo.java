package PrintInorder_1114.MonitorLocks;

class Foo {
    int turn;

    public synchronized void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        while (this.turn != 0) {
            wait();
        }
        printFirst.run();
        this.turn = 1;
        notifyAll();
    }

    public synchronized void second(Runnable printSecond) throws InterruptedException {
        
        // printSecond.run() outputs "second". Do not change or remove this line.
        while (this.turn != 1) {
            wait();
        }
        printSecond.run();
        this.turn = 2;
        notifyAll();
    }

    public synchronized void third(Runnable printThird) throws InterruptedException {
        
        // printThird.run() outputs "third". Do not change or remove this line.
        while (this.turn != 2) {
            wait();
        }
        printThird.run();
    }
}