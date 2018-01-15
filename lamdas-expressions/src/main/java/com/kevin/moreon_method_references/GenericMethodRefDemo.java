package com.kevin.moreon_method_references;

/**
 * Created by Kevin Kimaru Chege on 8/14/2017.
 */
public class GenericMethodRefDemo {
// This method has the MyFunc functional interface as the
// type of its first parameter. The other two parameters
// receive an array and a value, both of type T.
    public static <T> int myOp (FunctMy<T> f, T[] vals, T v) {
        return f.func(vals, v);
    }

}
