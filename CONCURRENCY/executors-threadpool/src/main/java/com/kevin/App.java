package com.kevin;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class MyTask implements Runnable {
    String name;
    CountDownLatch latch;

    MyTask(CountDownLatch latch, String name) {
        this.latch = latch;
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(name + " " + i);
            latch.countDown();
        }
    }
}

public class App {
    public static void main(String[] args) {
        CountDownLatch cl = new CountDownLatch(5);
        CountDownLatch cl1 = new CountDownLatch(5);
        CountDownLatch cl2 = new CountDownLatch(5);
        CountDownLatch cl3 = new CountDownLatch(5);

        ExecutorService es = Executors.newFixedThreadPool(2);

        System.out.println("Starting");

        //starting new threads
        es.execute(new MyTask(cl, "A"));
        es.execute(new MyTask(cl1, "B"));
        es.execute(new MyTask(cl2, "C"));
        es.execute(new MyTask(cl3, "D"));

        try {
            cl.await();
            cl1.await();
            cl2.await();
            cl3.await();
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        es.shutdown();
        System.out.println("Done");
    }

}
