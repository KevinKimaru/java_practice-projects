package com.kevin.call;

/**
 * Created by Kevin Kimaru Chege on 8/12/2017.
 */
public class Caller implements Runnable {
    String msg;
    CallMe target;
    Thread t;

    public Caller(CallMe targ, String s) {
        target = targ;
        msg = s;
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        target.call(msg);
    }

//        //use this if target.call is not synchronized
//    @Override
//    public void run() {
//        synchronized (target) {
//            target.call(msg);
//        }
//    }

    public Thread getT() {
        return t;
    }
}
