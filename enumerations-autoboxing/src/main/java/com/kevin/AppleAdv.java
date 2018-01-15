package com.kevin;

/**
 * Created by Kevin Kimaru Chege on 8/12/2017.
 */
public enum AppleAdv {
    Jonathan(10), GoldenBel(9), RedBel(12), Winesap(15), Cortland(8);

    private int price;

    //Constructor
    AppleAdv(int p) {
        price = p;
    }

    int getPrice() {return price;}
}
