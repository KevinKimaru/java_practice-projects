package com.kevin;

import java.util.concurrent.atomic.AtomicInteger;

class Shared {
    static AtomicInteger ai = new AtomicInteger(0);
//    static int i = 0;
}

class AtomThread implements Runnable {
    String name;

    public AtomThread(String name) {
        this.name = name;
        new Thread(this).start();
    }

    @Override
    public void run() {
        System.out.println("Starting " + name);
        for (int i = 0; i <= 3; i++) {
            System.out.println(name + " got: " + Shared.ai.getAndSet(i));
            System.out.println(name + " value now is:" + Shared.ai);
//            System.out.println(name + " got:" + Shared.i);
//            Shared.i = i;
//            System.out.println(name + " value now is:" + Shared.i);
        }
    }
}

public class App {
    public static void main(String[] args) {
        new AtomThread("A");
        new AtomThread("B");
        new AtomThread("C");
    }
}
