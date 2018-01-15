package com.kevin;

import com.kevin.exception_handling.ExceptionDemo;
import com.kevin.exception_handling.MyException;
import com.kevin.figurePackageUsingAbstract.Figure;
import com.kevin.figurePackageUsingAbstract.Rectangle;
import com.kevin.figurePackageUsingAbstract.Triangle;

import javax.swing.*;

/**
 * Created by Kevin Kimaru Chege on 8/11/2017.
 */
public class App {
    public static void main(String[] args) {
//        Rectangle rectangle = new Rectangle(9, 5);
//        Triangle triangle = new Triangle(10, 8);
//
//        Figure figure;
//
//        figure = rectangle;
//        System.out.println("Area is " + figure.area());
//
//        figure = triangle;
//        System.out.println("Area is " + figure.area());

        try {
            ExceptionDemo.compute(4);
            ExceptionDemo.compute(20);
        } catch (MyException e) {
            System.out.println("Caughr" + e);
        }

    }
}
