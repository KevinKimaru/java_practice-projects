package com.kevin.simple_factorial;

/**
 * Created by Kevin Kimaru Chege on 8/15/2017.
 */
public class MyClass<T> {
    private T val;

    // A constructor that takes an argument.
    public MyClass(T v) {
        val = v;
    }

    // The default constructor. This constructor
    // is NOT used by this program.
    public MyClass() {
        val = null;
    }
    // ...

    public T getVal() {
        return val;
    }

    ;
}
