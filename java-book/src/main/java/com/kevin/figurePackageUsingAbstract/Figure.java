package com.kevin.figurePackageUsingAbstract;

/**
 * Created by Kevin Kimaru Chege on 8/12/2017.
 */
public abstract class Figure {
    double dim1;
    double dim2;

    public Figure(double a, double b) {
        dim1 = a;
        dim2 = b;
    }

    public abstract double area();
}
