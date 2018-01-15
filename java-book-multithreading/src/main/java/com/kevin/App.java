package com.kevin;

import com.kevin.call.CallMe;
import com.kevin.call.Caller;
import com.kevin.consumer_producer.Consumer;
import com.kevin.consumer_producer.Producer;
import com.kevin.consumer_producer.Q;

/**
 * Created by Kevin Kimaru Chege on 8/12/2017.
 */
public class App {
    public static void main(String[] args) {
//        IntroThrads();
//        threadEx();

//        multithreading();

//        useOfSynchronized();

        notifyWait();

//        suspend_resume_thread();
    }

    private static void suspend_resume_thread() {
        NewThread ob1 = new NewThread("THread 1");
        NewThread ob2 = new NewThread("THread 2");

        try {
            Thread.sleep(1000);
            ob1.mySuspend();
            System.out.println("Suspending thread one");
            Thread.sleep(1000);
            ob1.myResume();
            System.out.println("Resuming thread one");
            ob2.mySuspend();
            System.out.println("Suspending thread two");
            Thread.sleep(1000);
            ob2.myResume();
            System.out.println("Resuming thread two");
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted");
        }

//         wait for threads to finish
        try {
            System.out.println("Waiting for threads to finish.");
            ob1.t.join();
            ob2.t.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread Interrupted");
        }

        System.out.println("Main thread exiting.");
    }

    private static void notifyWait() {
        Q q = new Q();
        new Producer(q);
        new Consumer(q);

        System.out.println("Press Control-C to stop");
    }

    private static void useOfSynchronized() {
        CallMe target = new CallMe();
        Caller ob1 = new Caller(target, "Hello");
        Caller ob2 = new Caller(target, "Synchronized");
        Caller ob3 = new Caller(target, "Java");

        //wait for the threads to end
        try {
            ob1.getT().join();
            ob2.getT().join();
            ob3.getT().join();
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
    }

    private static void multithreading() {
        DemoThread ob1 = new DemoThread("Thread1");
        DemoThread ob2 = new DemoThread("Thread2");
        DemoThread ob3 = new DemoThread("Thread3");

//        ob1.t.setPriority(6);

        System.out.println("Thread One is alive: " + ob1.t.isAlive());
        System.out.println("Thread Two is alive: " + ob2.t.isAlive());
        System.out.println("Thread Three is alive: " + ob3.t.isAlive());

        try {
            //wait for other threads to end
            System.out.println("Waiting for threads to finish.");
            ob1.t.join();
            ob2.t.join();
            ob3.t.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }

        System.out.println("Thread One is alive: " + ob1.t.isAlive());
        System.out.println("Thread Two is alive: " + ob2.t.isAlive());
        System.out.println("Thread Three is alive: " + ob3.t.isAlive());

        System.out.println("Main thread exiting.");
    }

    private static void threadEx() {
        //create a new thread
        new DemoThread("Demo Thread");

        try {
            for (int i = 5; i > 0; i--) {
                System.out.println("Main thread " + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        System.out.println("Main thread exiting.");
    }

    private static void IntroThrads() {
        Thread thread = Thread.currentThread();

        System.out.println("Current Thread:" + thread);

        //change the name of the thread
        thread.setName("My Thread");
        System.out.println("After name changed" + thread);

        try {
            for (int i = 5; i > 0; i--) {
                System.out.println(i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted");
        }
    }
}
