package com.kevin;

import java.util.concurrent.Phaser;

// Extend Phaser and override onAdvance() so that only a specific
// number of phases are executed.

// Extend MyPhaser to allow only a specific number of phases  to be executed.
class MyPhaser extends Phaser {
    int numPhases;

    MyPhaser(int parties, int phaseCount) {
        super(parties);
        numPhases = phaseCount - 1;
    }

    // Override onAdvance() to execute the specified  number of phases.
    @Override
    protected boolean onAdvance(int phase, int registeredParties) {
        // This println() statement is for illustration only.
        // Normally, onAdvance() will not display output.
        System.out.println("Phase " + phase + " completed.\n");

        // If all phases have completed, return true
        if (phase == numPhases || registeredParties == 0) return true;
        return false;
    }
}

class MyThread implements Runnable {
    Phaser phsr;
    String name;

    MyThread(Phaser p, String n) {
        phsr = p;
        name = n;
        phsr.register();
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (!phsr.isTerminated()) {
            System.out.println("Thread " + name + " Beginning Phase " + phsr.getPhase());
            System.out.println("Arrived parties: "+  phsr.getArrivedParties());
            System.out.println("Unarrived parties: "+  phsr.getUnarrivedParties());
            phsr.arriveAndAwaitAdvance();

            // Pause a bit to prevent jumbled output. This is for illustration
            // only. It is not required for the proper operation of the phaser.
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}

public class App {
    public static void main(String[] args) {
        MyPhaser p = new MyPhaser(1, 4);

        System.out.println("Starting\n");

        new MyThread(p, "A");
        new MyThread(p, "B");
        new MyThread(p, "C");

        System.out.println("number of registered parties is: "+  p.getRegisteredParties());

        while (!p.isTerminated()) {
            System.out.println("Arrived parties: "+  p.getArrivedParties());
            System.out.println("Unarrived parties: "+  p.getUnarrivedParties());
            p.arriveAndAwaitAdvance();
        }

        System.out.println("The phaser is terminated");
    }
}
