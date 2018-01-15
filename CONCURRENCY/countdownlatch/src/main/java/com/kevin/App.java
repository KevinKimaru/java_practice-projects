package com.kevin;

import java.util.concurrent.CountDownLatch;

class MyThread implements Runnable {
    CountDownLatch latch;

    MyThread(CountDownLatch latch) {
        this.latch = latch;
        new Thread(this).start();
    }

    @Override
    public void run() {
        for (int i =0 ; i<5; i++) {
            System.out.println(i);
            latch.countDown();
        }
    }
}
public class App {
    public static void main(String[] args) {
        CountDownLatch cdl = new CountDownLatch(3);
        System.out.println("Starting");

        new MyThread(cdl);

        try {
            cdl.await();
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        System.out.println("Done");
    }
}
