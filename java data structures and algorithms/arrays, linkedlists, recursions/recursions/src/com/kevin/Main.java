package com.kevin;

import java.net.URL;
import java.util.Arrays;

public class Main {
    /**
     * Class to draw a ruler eg:
     * ---- 0
     * -
     * --
     * -
     * ---
     * -
     * --
     * -
     * ----1
     */

    private static class Ruler{
        /**
         * draw a tick with no label
         */
        private static void drawOneTick(int tickLength) {
            drawOneTick(tickLength, -1);
        }

        /**
         * draw one tick eg ---
         */
        private static void drawOneTick(int tickLength, int tickLabel) {
            for (int i = 0; i < tickLength; i++)
                System.out.print("-");
            if (tickLabel >= 0) System.out.print(" " + tickLabel);
            System.out.println("\n");
        }

        private static void drawTicks(int tickLength) { //draw ticks of given length
            if (tickLength > 0) {       //stop when length drops to 0
                drawTicks(tickLength - 1); //recursively draw left ticks
                drawOneTick(tickLength);        //draw center tick
                drawTicks(tickLength - 1); //recursively draw right ticks
            }
        }

        /**
         * @param inches      eg 0, 1, 2, 3
         * @param majorLength no. of dashes for the mains eg ----(4)
         */
        static void drawRuler(int inches, int majorLength) { //draw ruler
            drawOneTick(majorLength, 0); //draw tick 0 and its label
            for (int i = 1; i <= inches; i++) {
                drawTicks(majorLength - 1); //draw ticks for this inch
                drawOneTick(majorLength, i); //draw ticks i and its label
            }
        }
    }

    public static void main(String[] args) {
//        System.out.println(factorialRecurion(5) + "");
//        Ruler.drawRuler(2, 5);
//        int k[] = {5, 9, 2, 1, 4, 10, 1, 1};
//        System.out.println(sumArray(k, 8));
//        System.out.println(binarySum(k, 0, 6));
//        System.out.println(Arrays.toString(iterativeReverseArray(k, 0, k.length - 1)));
//        System.out.println(Arrays.toString(reverseArray(k, 0, k.length - 1)));
//        Root r = new Root();
//        r.getRoot();
        System.out.println(iterativeFibb(3));
        System.out.println(binaryFibb(3));
    }

    private static class Root {
        public Root() {
        }

        private void getRoot() {
            System.out.println(System.getProperty("user.dir"));
            URL u = getClass().getProtectionDomain().getCodeSource().getLocation();
            System.out.println(u);
        }
    }

    private static int factorialRecurion(int n) {
        if (n == 0) return 1;
        else return n * factorialRecurion(n - 1);
    }

    /**
     * @param a array whose elements to be summed
     * @param n no. of elements from @param a to sum
     */
    private static int sumArray(int[] a, int n) {
        if (n == 1) return a[0];
        else return sumArray(a, n - 1) + a[n - 1];
    }

    private static int[] reverseArray(int[] a, int i, int j) {
        if (i < j) {//If n is odd, we will eventually reach the i = j case, and if n is even, we will eventually reach the i > j case
            int k = a[i];
            a[i] = a[j];
            a[j] = k;
            reverseArray(a, i + 1, j - 1);
        }
        return a;
    }

    private static int[] iterativeReverseArray(int[] a, int i, int j) {
        while (i < j) {
            int k = a[i];
            a[i] = a[j];
            a[j] = k;
            i = i + 1;
            j = j - 1;
        }
        return a;
    }

    /**
     * @param i is index to start
     * @param n is number of elements to add and must be a power of 2
     */
    private static int binarySum(int[] a, int i, int n) {
        if (n == 1) return a[i];
        else return binarySum(a, i, n / 2) + binarySum(a, i + n / 2, n / 2);
    }

    //Fibbonacci sequence: n0=1, n1=1, n2=n1 + n0 = 2, n3=n2 + n1 = (n1 + n0) + n1 = 5
    //this is the not the best way to handle fibonnacci. it is very intensive.
    private static int binaryFibb(int k) {
        if (k <= 1) return 1;
        else return binaryFibb(k - 1) + binaryFibb(k - 2);
    }

    private static int iterativeFibb(int k) {
        int a = 0;
        int b = 1;

        while (k >= 1) {
            int temp = b;
            b += a;
            a = temp;
            k--;
        }
        return b;
    }
}
