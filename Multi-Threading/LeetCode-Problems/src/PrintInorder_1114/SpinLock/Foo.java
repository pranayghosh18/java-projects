package PrintInorder_1114.SpinLock;

class Foo {
    int turn;

    public Foo() {
        this.turn = 0;
    }

    public void first(Runnable printFirst) throws InterruptedException {
        
        // printFirst.run() outputs "first". Do not change or remove this line.
        while (turn != 0) {
            // infinite loop of wait
        }
        printFirst.run();
        turn = 1;
    }

    public void second(Runnable printSecond) throws InterruptedException {
        
        // printSecond.run() outputs "second". Do not change or remove this line.
        while (turn != 1) {
            // infinite loop of wait
        }
        printSecond.run();
        turn = 2;
    }

    public void third(Runnable printThird) throws InterruptedException {
        
        // printThird.run() outputs "thirst". Do not change or remove this line.
        while (turn != 2) {
            // no req of wait()
        }
        printThird.run();
    }
}