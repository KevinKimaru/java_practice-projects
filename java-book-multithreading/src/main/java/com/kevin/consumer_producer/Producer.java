package com.kevin.consumer_producer;

/**
 * Created by Kevin Kimaru Chege on 8/12/2017.
 */
public class Producer implements Runnable {
    Q q;

    public Producer(Q q) {
        this.q = q;
        new Thread(this, "Producer").start();
    }

    @Override
    public void run() {
        int i = 0;

        while(true) {
            q.put(i++);
        }
    }
}
