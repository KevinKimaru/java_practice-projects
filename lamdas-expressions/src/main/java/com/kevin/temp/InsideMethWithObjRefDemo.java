package com.kevin.temp;

/**
 * Created by Kevin Kimaru Chege on 8/14/2017.
 */
public class InsideMethWithObjRefDemo {
    // A method that returns the number of occurrences
    // of an object for which some criteria, as specified by
    // the MyFunc parameter, is true.
    public static <T> int counter(T[] vals, MyFuncTemp<T> f, T v) {
        int count = 0;

        for (int i = 0; i < vals.length; i++) {
            if (f.func(vals[i],  v)) count++;
        }
        return count;
    }
}
