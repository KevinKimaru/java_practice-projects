package com.kevin.genhierachy;

/**
 * Created by Kevin Kimaru Chege on 8/14/2017.
 */
public class Gen2<T, V> extends Gen<T> {
    V ob2;
    public Gen2(T o, V o2) {
        super(o);
        ob2 = o2;
    }

    public V getOb2() {
        return ob2;
    }
}
