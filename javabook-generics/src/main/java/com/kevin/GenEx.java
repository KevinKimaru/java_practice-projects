package com.kevin;

// A simple generic class. Here, T is a type parameter that will be replaced by a real type when an object of type Gen is created.
public class GenEx<T> {
    T ob;  // declare an object of type T

    // Pass the constructor a reference to an object of type T.
    GenEx(T o) {
        ob = o;
    }

    T getOb() {
        return ob;
    }

    // Show type of T.
    void showType() {
        System.out.println("TYpe of T is:" + ob.getClass().getName());
    }
}
