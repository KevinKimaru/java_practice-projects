package com.kevin.moreon_method_references;

/**
 * Created by Kevin Kimaru Chege on 8/14/2017.
 */
// A functional interface that operates on an array
// and a value, and returns an int result.
public interface FunctMy<T> {
    int func(T[] vals, T v);
}

