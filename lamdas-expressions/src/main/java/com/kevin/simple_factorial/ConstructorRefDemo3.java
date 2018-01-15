package com.kevin.simple_factorial;

/**
 * Created by Kevin Kimaru Chege on 8/15/2017.
 */
public class ConstructorRefDemo3 {
    // A factory method for class objects. The class must
    // have a constructor that takes one parameter of type T.
    // R specifies the type of object being created.
    public static <R, T> R myClassFactory(MyFunc<R, T> cons, T v) {
        return cons.func(v);
    }
}
