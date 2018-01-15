package com.kevin;

/**
 * Created by Kevin Kimaru Chege on 8/13/2017.
 */
public class StackExtend<T extends Number> {
    T[] nums;

    StackExtend(T[] o) {
        nums = o;
    }

    // Pass the constructor a reference to an array of type Number or subclass.
    double average() {
        double sum = 0.0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i].doubleValue();
        }

        return sum/ nums.length;
    }
    // Determine if two averages are the same. Notice the use of the wildcard.
    boolean sameAvg(StackExtend<?> o) {
        return average() == o.average();
    }
}
