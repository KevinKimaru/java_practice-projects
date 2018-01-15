package com.kevin.boxPackage;

import com.kevin.boxPackage.Box;
import com.kevin.boxPackage.BoxWeight;

// Here, Box is extended to include color.
class ColorBox extends Box {
    int color; // color of box

    // construct clone of an object
    ColorBox(Box ob, int c) {// pass object to constructor
        super(ob);
        color = c;
    }

    // constructor when all parameters are specified
    ColorBox(double w, double h, double d, double m, int c) {
        super(w, h, d); // call superclass constructor
        color = c;
    }

    // default constructor
    ColorBox() {
        super();
        color = -1;
    }

    // constructor used when cube is created
    ColorBox(double len, double m, int c) {
        super(len);
        color = c;
    }
}
