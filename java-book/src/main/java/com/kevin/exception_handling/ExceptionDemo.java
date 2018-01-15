package com.kevin.exception_handling;

/**
 * Created by Kevin Kimaru Chege on 8/12/2017.
 */
public class ExceptionDemo {
    public static void compute(int a) throws MyException {
        System.out.println("Called compute(" + a + ")");
        if (a > 10) throw new MyException(a);
        System.out.println("Normal exit");
    }
}
