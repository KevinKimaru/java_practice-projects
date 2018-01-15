package com.kevin;

import java.util.concurrent.Exchanger;

//A thread that constructs a string
class  MakeString implements Runnable {
    Exchanger<String> e;
    String str;

    MakeString(Exchanger<String> e) {
        this.e = e;
        str = new String();
        new Thread(this).start();
    }

    @Override
    public void run() {
        char ch = 'A';
        for (int i = 0; i<3; i++) {
            for (int j = 0; j<3; j++) str += ch++;

            try {
                str = e.exchange(str);
            } catch (InterruptedException e1) {
                System.out.println(e);
            }

        }
    }
}

class  UseString implements Runnable {
    Exchanger<String> e;
    String str;

    UseString(Exchanger<String> e) {
        this.e = e;
        new Thread(this).start();
    }

    @Override
    public void run() {
        char ch = 'A';
        for (int i = 0; i<3; i++) {
            try {
                str = e.exchange(new String("M"));
                System.out.println("Got " + str);
            } catch (InterruptedException e1) {
                System.out.println(e);
            }

        }
    }
}

public class App {
    public static void main(String[] args) {
        Exchanger<String> e = new Exchanger<>();
        new MakeString(e);
        new UseString(e);
    }
}
