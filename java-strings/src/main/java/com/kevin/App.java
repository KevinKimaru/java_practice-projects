package com.kevin;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by Kevin Kimaru Chege on 8/15/2017.
 */
class TestClone implements Cloneable {
    int a;
    double b;

    // This method calls Object's clone().
    TestClone cloneTest() {
        try {
            //call clone in object
            return (TestClone) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("Cloning not allowed");
            return this;
        }
    }
}

class TestClone2 implements Cloneable {
    int a;
    double b;

    @Override
    protected Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("Cloning not allowed");
            return this;
        }
    }
}

public class App {
    public static void main(String[] args) {
//        sortString();

//        use_substr();

//        use_stringbuffer();

//        processes_runtime();

//        system_time();

//        arraycopy();

//        cloning();

//        threads();


    }

    private static void threads() {
        List<Integer> li = new ArrayList<>();
        ThreadGroup groupA = new ThreadGroup("Group A");
        ThreadGroup groupB = new ThreadGroup("Group B");

        NewThread ob1 = new NewThread("one", groupA);
        NewThread ob2 = new NewThread("two", groupA);
        NewThread ob3 = new NewThread("three", groupB);
        NewThread ob4 = new NewThread("four", groupB);

        System.out.println("\nHere is output from list():");
        groupA.list();
        groupB.list();
        System.out.println();

        System.out.println("Suspending group A");
        Thread tga[] = new Thread[groupA.activeCount()];
        groupA.enumerate(tga); // get threads in group
        for (int i = 0; i < tga.length; i++) {
            ((NewThread) tga[i]).mySuspend();//suspend each thread
        }

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }

        System.out.println("Resuming Group A");
        for (int i = 0; i < tga.length; i++) {
            ((NewThread) tga[i]).myResume();// resume threads in group
        }

        // wait for threads to finish
        try {
            System.out.println("Waiting for threads to finish.");
            ob1.join();
            ob2.join();
            ob3.join();
            ob4.join();
        } catch (Exception e) {
            System.out.println("Exception in Main thread");
        }

        System.out.println("Main thread exiting.");
    }

    private static void cloning() {
        TestClone x1 = new TestClone();
        TestClone x2;

        x1.a = 10;
        x1.b = 20.98;

        x2 = x1.cloneTest();//clone x1
        System.out.println("x1: " + x1.a + " " + x1.b);
        System.out.println("x2: " + x2.a + " " + x2.b);

        TestClone2 x12 = new TestClone2();
        TestClone2 x22;

        x12.a = 10;
        x12.b = 20.98;

        x22 = (TestClone2) x12.clone();

        System.out.println("x1: " + x1.a + " " + x1.b);
        System.out.println("x2: " + x2.a + " " + x2.b);
    }

    private static void arraycopy() {
        byte a[] = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74};
        byte b[] = {77, 77, 77, 77, 77, 77, 77, 77, 77, 77};

        System.out.println("a = " + new String(a));
        System.out.println("b = " + new String(b));

        System.arraycopy(a, 0, b, 0, a.length);
        System.out.println("a = " + new String(a));
        System.out.println("b = " + new String(b));

        System.arraycopy(a, 0, a, 1, a.length - 1);
        System.arraycopy(b, 1, b, 0, b.length - 1);
        System.out.println("a = " + new String(a));
        System.out.println("b = " + new String(b));

        System.out.println(System.getProperty("user.dir"));
    }

    private static void system_time() {
        long end = 0, start;
//        start = System.currentTimeMillis();
        start = System.nanoTime();
        System.out.println("Starting time: " + start);
        for (long i = 0; i <= 1000000000L; i++)
//            end = System.currentTimeMillis();
            end = System.nanoTime();
        System.out.println("End Time: " + end);
        System.out.println("Elapsed time: " + (end - start));
    }

    private static void processes_runtime() {
        Runtime r = Runtime.getRuntime();
        System.out.println(r.totalMemory());
        System.out.println(r.freeMemory());
        r.gc();
        System.out.println(r.freeMemory());

//        Process p =null;
//        try {
//            p = r.exec("notepad");
//            p.waitFor();
//        } catch (IOException e) {
//            System.out.println("Error executing notepad");
//        } catch (InterruptedException e) {
//            System.out.println("Interrupted");
//        }
//        System.out.println("Notepad returned " + p.exitValue());


        try {
            ProcessBuilder proc = new ProcessBuilder("notepad.exe", "testfile");
            proc.start();
        } catch (IOException e) {
            System.out.println("Error executing notepad");
        }
    }

    private static void use_stringbuffer() {
        StringBuffer sb = new StringBuffer("Hello");
        System.out.println("buffer before = " + sb);
        System.out.println("charAt(1) before = " + sb.charAt(1));

        sb.setCharAt(1, 'i');
        sb.setLength(2);
        System.out.println("buffer after = " + sb);
        System.out.println("charAt(1) after = " + sb.charAt(1));
    }

    private static void use_substr() {
        String org = "This is a test. This is, too.";
        String search = "is";
        String sub = "was";
        String result = "";
        int i;

        do { // replace all matching substrings
            System.out.println(org);
            i = org.indexOf(search);
            if (i != -1) {
                result = org.substring(0, i);
                result = result + sub;
                result = result + org.substring(i + search.length());
                org = result;

            }

        } while (i != -1);
    }

    private static void sortString() {
        String arr[] = {"Now", "is", "the", "time", "for", "all", "good", "men", "to", "come", "to", "the", "aid", "of", "their", "country"};
        for (int j = 0; j < arr.length; j++) {
            for (int i = j + 1; i < arr.length; i++) {
                if (arr[i].compareTo(arr[j]) < 0) {
                    String t = arr[j];
                    arr[j] = arr[i];
                    arr[i] = t;
                }
            }
            System.out.println(arr[j]);
        }
    }
}

