package com.kevin.max;

/**
 * Created by Kevin Kimaru Chege on 8/14/2017.
 */
public class UseMethodRef {
    // A compare() method compatible with the one defined by Comparator<T>.
    public static int compareMC(MyClass a, MyClass b) {
        return a.getVal() - b.getVal();
    }
}
