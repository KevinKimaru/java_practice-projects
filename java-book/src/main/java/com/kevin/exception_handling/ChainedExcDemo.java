package com.kevin.exception_handling;

/**
 * Created by Kevin Kimaru Chege on 8/12/2017.
 */
public class ChainedExcDemo {
    public  static  void demo () {
        //create an exception
        NullPointerException e = new NullPointerException("top layer");

        //add a cause exception
        e.initCause(new ArithmeticException("cause"));

        throw e;
    }

    public static void workout() {
        try {
            demo();
        } catch (NullPointerException e) {
            //gets top level exception
            System.out.println("Caught: " + e);
//            display cause exception
            System.out.println("Original cause: " + e.getCause());
        }
    }

}
