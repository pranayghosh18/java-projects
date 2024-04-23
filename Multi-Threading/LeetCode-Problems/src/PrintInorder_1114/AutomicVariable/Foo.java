package PrintInorder_1114.AutomicVariable;

import java.util.concurrent.atomic.AtomicInteger;

class Foo {
    // automatic integet prevent using synchronized
    AtomicInteger autoTurn;

    public Foo() {
        autoTurn = new AtomicInteger();
        autoTurn.set(0);
    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        while (this.autoTurn.get() != 0) {
            // no req of wait()
        }
        printFirst.run();
        this.autoTurn.set(1);
        // also not req notifyAll();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        // printSecond.run() outputs "second". Do not change or remove this line.
        while (this.autoTurn.get() != 1) {
            // no req of wait()
        }
        printSecond.run();
        this.autoTurn.set(2);
        // also not req notifyAll();
    }

    public void third(Runnable printThird) throws InterruptedException {
        // printThird.run() outputs "thirst". Do not change or remove this line.
        while (this.autoTurn.get() != 2) {
            // no req of wait()
        }
        printThird.run();
        // also not req notifyAll();
    }
}