package com.kevin.figurePackage;

/**
 * Created by Kevin Kimaru Chege on 8/12/2017.
 */
public class Rectangle extends Figure{
    public Rectangle(double a, double b) {
        super(a, b);
    }

    public double area() {
        System.out.println("Inside area of rectangle:");
        return dim1 * dim2;
    }
}
