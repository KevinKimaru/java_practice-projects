package com.kevin.call;

/**
 * Created by Kevin Kimaru Chege on 8/12/2017.
 */
public class CallMe {
    synchronized public void call(String msg) {
        System.out.print("[" + msg);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
        System.out.println("]");
    }
}
