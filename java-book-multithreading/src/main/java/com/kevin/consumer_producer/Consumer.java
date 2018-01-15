package com.kevin.consumer_producer;

/**
 * Created by Kevin Kimaru Chege on 8/12/2017.
 */
public class Consumer implements Runnable {
    Q q;

    public Consumer (Q q) {
        this.q = q;
        new Thread(this, "Consumer").start();
    }

    @Override
    public void run() {
        while (true) q.get();
    }
}
