package com.kevin.figurePackage;

/**
 * Created by Kevin Kimaru Chege on 8/12/2017.
 */
public class Triangle extends Figure {
    public Triangle(double a, double b) {
        super(a, b);
    }

    public double area() {
        System.out.println("Inside area of triangle:");
        return dim1 * dim2 / 2;
    }
}
