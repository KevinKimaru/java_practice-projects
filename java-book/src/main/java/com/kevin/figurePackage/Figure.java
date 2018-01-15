package com.kevin.figurePackage;

/**
 * Created by Kevin Kimaru Chege on 8/12/2017.
 */
public class Figure {
    double dim1;
    double dim2;

    public Figure(double a, double b) {
        dim1 = a;
        dim2 = b;
    }

    public double area() {
        System.out.println("Area for figure is undefined:");
        return 0;
    }
}
