package com.kevin.temp;

/**
 * Created by Kevin Kimaru Chege on 8/14/2017.
 */
// A functional interface that takes two reference arguments
// and returns a boolean result.
public interface MyFuncTemp<T> {
    boolean func(T v1, T v2);
}
