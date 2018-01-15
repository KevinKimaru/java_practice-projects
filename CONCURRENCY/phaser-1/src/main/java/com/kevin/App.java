package com.kevin;

import java.util.concurrent.Phaser;

class MyThread implements Runnable {
    Phaser p = new Phaser();
    String name;

    MyThread(Phaser ph, String name) {
        this.name = name;
        p = ph;
        p.register();
        new Thread(this).start();
    }

    @Override
    public void run() {
        System.out.println("Thread " + name + " beginning phase one.");
        p.arriveAndAwaitAdvance();//signal arrival

        // Pause a bit to prevent jumbled output. This is for illustration
        // only. It is not required for the proper operation of the phaser.
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        System.out.println("Thread " + name + " beginning phase two.");
        p.arriveAndAwaitAdvance();//signal arrival

        // Pause a bit to prevent jumbled output. This is for illustration
        // only. It is not required for the proper operation of the phaser.
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        System.out.println("Thread " + name + " beginning phase three.");
        p.arriveAndDeregister();//signal arrival
    }
}

public class App {
    public static void main(String[] args) {
        Phaser p = new Phaser(1);
//        p.register();

        int curPhase;

        System.out.println("Starting");

        new MyThread(p, "A");
        new MyThread(p, "B");
        new MyThread(p, "C");

        // Wait for all threads to complete phase one.
        curPhase = p.getPhase();
        p.arriveAndAwaitAdvance();
        System.out.println("Phase " + curPhase + " Complete");

        // Wait for all threads to complete phase two.
        curPhase = p.getPhase();
        p.arriveAndAwaitAdvance();
        System.out.println("Phase " + curPhase + " Complete");

        // Wait for all threads to complete phase three.
        curPhase = p.getPhase();
        p.arriveAndAwaitAdvance();
        System.out.println("Phase " + curPhase + " Complete");

        // Deregister the main thread.
        p.arriveAndDeregister();
        if (p.isTerminated()) System.out.println("The phaser is terminated");

    }
}
