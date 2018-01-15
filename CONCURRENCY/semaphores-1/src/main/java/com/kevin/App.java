package com.kevin;

import java.util.concurrent.Semaphore;

class Shared {
    static int count = 0;
}

class IncThread implements Runnable {
    String name;
    Semaphore sem;

    IncThread(Semaphore e, String n) {
        name = n;
        sem = e;
        new Thread(this).start();
    }

    @Override
    public void run() {
        System.out.println("Starting" + name);

        try {
            //First get a permit
            System.out.println(name + " is waiting for a permit.");
            sem.acquire();
            System.out.println(name + " gets a permit");

            //Now access shared resources
            for (int i = 0; i < 5; i++) {
                Shared.count++;
                System.out.println(name + ": " + Shared.count);

                // Now, allow a context switch -- if possible.
                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        System.out.println(name + " releasing the permit");
        sem.release();
    }
}

class DecThread implements Runnable {
    String name;
    Semaphore sem;

    DecThread(Semaphore e, String n) {
        name = n;
        sem = e;
        new Thread(this).start();
    }

    @Override
    public void run() {
        System.out.println("Starting" + name);

        try {
            //First get a permit
            System.out.println(name + " is waiting for a permit.");
            sem.acquire();
            System.out.println(name + " gets a permit");

            //Now access shared resources
            for (int i = 0; i < 5; i++) {
                Shared.count--;
                System.out.println(name + ": " + Shared.count);

                // Now, allow a context switch -- if possible.
                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        System.out.println(name + " releasing the permit");
        sem.release();
    }
}

public class App {
    public static void main(String[] args) {
        Semaphore sem = new Semaphore(1);
        new IncThread(sem, "A");
        new DecThread(sem, "B");
    }

}
