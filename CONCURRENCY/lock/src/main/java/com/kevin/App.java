package com.kevin;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Shared {
    static int count = 0;
}

class LockThread implements Runnable {
    String name;
    ReentrantLock lock;

    public LockThread(ReentrantLock lock, String name) {
        this.lock = lock;
        this.name = name;
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            System.out.println("Starting " + name);

            System.out.println(name + " is waiting to lock count.");
            lock.lock();
            System.out.println(name + " is locking count.");

            Shared.count++;
            System.out.println(name + ": " + Shared.count);

            System.out.println(name + " is sleeping");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e);
        } finally {
            System.out.println(name + " is unlocking account.");
            lock.unlock();
        }
    }
}

public class App {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();

        new LockThread(lock, "A");
        new LockThread(lock, "B");
    }
}
