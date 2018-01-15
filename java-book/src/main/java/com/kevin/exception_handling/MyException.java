package com.kevin.exception_handling;

/**
 * Created by Kevin Kimaru Chege on 8/12/2017.
 */
public class MyException extends Exception {
    int detail;
    MyException(int a) {
        detail = a;
    }

    @Override
    public String toString() {
        return "MyException[" + detail + "]";
    }
}
