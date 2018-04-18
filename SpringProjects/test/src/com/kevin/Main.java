package com.kevin;

public class Main {


    public static void main(String[] args) {
        Testsyn testsyn = new Testsyn();
        new Thread1(testsyn);
        new Thread1(testsyn);


    }

    private static class Testsyn {

         void unsleep() {
             System.out.println("UNSLEPEP\n\n");
            try {
                for(int i = 0; i<10; i++) {
                    System.out.println(i);
                    Thread.sleep(1000);
                }
                System.out.println("UNSLEPEP\n\n");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        synchronized void sleep() {
            System.out.println("SLEPEP\n\n");
            try {
                for(int i = 0; i<10; i++) {
                    System.out.println(i);
                    Thread.sleep(1000);
                    if(i == 5) {
                        float k = i/0;
                    }

                }
                System.out.println("SLEEP\n\n");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class Thread1 implements Runnable {

        Thread thread;
        Testsyn testsyn;

        public Thread1(Testsyn testsyn) {
            this.testsyn = testsyn;
            thread = new Thread(this, "Thread1");
            thread.start();
        }

        @Override
        public void run() {
            testsyn.sleep();
        }
    }
}
