package com.kevin;

/**
 * Created by Kevin Kimaru Chege on 8/14/2017.
 */
public class GenericConstructors {
    private double val;

    public <T extends Number> GenericConstructors(T args) {
        val = args.doubleValue();
    }

    public void getVal() {
        System.out.println("Val: " + val);
    }
}
