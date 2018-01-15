package com.kevin.temp;

/**
 * Created by Kevin Kimaru Chege on 8/14/2017.
 */
public class HighTemp {
    private int hTemp;

    public HighTemp(int ht) {
        hTemp = ht;
    }

    // Return true if the invoking HighTemp object has the same   // temperature as ht2.
    public boolean sameTemp(HighTemp ht2) {
        return hTemp == ht2.hTemp;
    }

    // Return true if the invoking HighTemp object has a temperature   // that is less than ht2.
    public boolean lessThanTemp(HighTemp ht2) {
        return hTemp < ht2.hTemp;
    }
}
