package com.kevin;

import com.sun.glass.ui.Application;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Scores scores = new Scores();
        GameEntry e1 = new GameEntry("Kevin", 1000);
        GameEntry e2 = new GameEntry("Kim", 2000);
        GameEntry e3 = new GameEntry("Liz", 3000);
        GameEntry e4 = new GameEntry("Ann", 4000);
        GameEntry e5 = new GameEntry("Peris", 5000);
        GameEntry e6 = new GameEntry("Robert", 6000);
        GameEntry e7 = new GameEntry("Chege", 7000);
        GameEntry e8 = new GameEntry("Francis", 8000);
        GameEntry e9 = new GameEntry("Samuel", 9000);
        GameEntry e10 = new GameEntry("Rose", 10000);
        GameEntry e11 = new GameEntry("Eric", 20000);
        GameEntry e12 = new GameEntry("Max", 30000);

        scores.add(e1);
        scores.add(e7);
        scores.add(e8);
        scores.add(e2);
        scores.add(e9);
        scores.add(e10);
        scores.add(e5);
        scores.add(e3);
        scores.add(e4);
        scores.add(e6);
        scores.add(e11);
        scores.add(e12);

        System.out.println(scores.toString());

        System.out.printf("Removing game entry 5 : %s -> %d\n", e5.getName(), e5.getScore());
        scores.remove(4);
        System.out.println(scores.toString());

        char[] arr = {'e', 'd', 'l', 'a', 'f', 'c'};
        for (char b: arr) System.out.print(b + "\t");
        System.out.println();
        insertionSort(arr);
        for (char a: arr) System.out.print(a + "\t");
        System.out.println();

        randoms();
        System.out.println();

        pyramid();

    }

    private static void pyramid() {
        int count =0;
        for (int i = 0; i < 10; i++) {
            for (int j = 10; j >= count; j--) {
                System.out.print(" ");
            }
            for (int j = 0; j <= count; j++) {
                System.out.print("* ");
            }
            System.out.println();
            count++;
        }
    }

    //char[] arr = {'e', 'd', 'l', 'a', 'f', 'c'};
    //insertion sort array into increasing order
    public static void insertionSort(char[] a) {
        int n = a.length;
        for (int i = 1; i < n; i++) { //index from the second character in a[]
            char cur = a[i];            //the current character to be inserted
            System.out.println(cur + " " + i);
            int j = i - 1;              //start comparing with cell left of i
            while ((j >= 0) && (a[j] > cur)) { //while a[j] is graeter than cur, move a[j] right and decrement j;
//                a[j + 1] = a[j--]; //operation before assignment....so usishtuke
                a[j + 1] = a[j];
                j--;
                System.out.println("value of j is" + j);
                for (char k: a) System.out.print(k + "\t");
                System.out.println();
            }
            a[j+1] = cur;       //this is the proper place of cur
            System.out.println("After assignmengt Out of while value of j is" + j);
            for (char k: a) System.out.print(k + "\t");
            System.out.println();
        }
    }

    public static void randoms() {
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        int x = 0;
        while (x < 10) {
            System.out.print("\t" + random.nextInt(100));
            x++;
        }
        System.out.println();
    }

}
