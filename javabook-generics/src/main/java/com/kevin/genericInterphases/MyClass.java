package com.kevin.genericInterphases;

/**
 * Created by Kevin Kimaru Chege on 8/14/2017.
 */
public class MyClass<T extends Comparable<T>> implements MinMax<T>{
    T[] vals;

    public MyClass(T[] o) {vals = o;}
    @Override
    public T min() {
        T v = vals[0];
        for (int i = 0; i < vals.length; i++) {
            if (vals[i].compareTo(v) < 0) v = vals[i];
        }
        return v;
    }

    @Override
    public T max() {
        T v = vals[0];
        for (int i = 0; i < vals.length; i++) {
            if (vals[i].compareTo(v) > 0) v = vals[i];
        }
        return v;
    }
}
