package com.kevin.interfaces_package;

/**
 * Created by Kevin Kimaru Chege on 8/12/2017.
 */
public interface IntStack {
    void push(int item);
    int pop();
    default void clear() {
        System.out.println("clear() not implemented.");
    }
}
