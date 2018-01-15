package com.kevin.genericInterphases;

/**
 * Created by Kevin Kimaru Chege on 8/14/2017.
 */
public interface MinMax<T extends Comparable<T>> {
    T min();
    T max();
}
