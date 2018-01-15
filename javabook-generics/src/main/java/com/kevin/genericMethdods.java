package com.kevin;

/**
 * Created by Kevin Kimaru Chege on 8/14/2017.
 */
public class genericMethdods {
    static <T extends Comparable<T>, V extends T> boolean isIn(T x, V[] y) {
        for (int i = 0; i < y.length; i++) {
            return x.equals(y[i]);
        }
        return false;
    }
}
