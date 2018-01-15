package com.kevin.moreon_method_references;

/**
 * Created by Kevin Kimaru Chege on 8/14/2017.
 */

// This class defines a method called countMatching() that returns the number of items in an array that are equal
// to a specified value. Notice that countMatching()
// is generic, but MyArrayOps is not.
public class MyArrayOps {
    public static <T> int countMaching(T[]vals, T v) {
        int count = 0;

        for (int i = 0; i < vals.length; i++) {
            if (vals[i] == v) count++;
        }
        return count;
    }
}
