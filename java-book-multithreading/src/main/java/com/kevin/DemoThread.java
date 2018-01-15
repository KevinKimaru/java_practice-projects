package com.kevin;

/**
 * Created by Kevin Kimaru Chege on 8/12/2017.
 */
public class DemoThread implements Runnable {

    Thread t;
    String threadName;

    DemoThread(String name) {
        threadName = name;
        t = new Thread(this, name);
        System.out.println("Child thread: " + t);
        t.start();
    }

    @Override
    public void run() {
        try {
            for (int i = 5; i > 0; i--) {
                System.out.println(threadName + ":" + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Exiting child thread");
    }
}
