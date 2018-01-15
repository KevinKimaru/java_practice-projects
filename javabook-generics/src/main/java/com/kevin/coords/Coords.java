package com.kevin.coords;

/**
 * Created by Kevin Kimaru Chege on 8/14/2017.
 */
// This class holds an array of coordinate objects.
public class Coords<T extends TwoD> {
    T[] coords;

    public Coords(T[] o) { coords = o;}

    public static void showXY(Coords<?> c) {
        System.out.println("X and Y coordinates");
        for (int i = 0; i < c.coords.length; i++)
            System.out.println(c.coords[i].x + " " + c.coords[i].y);
        System.out.println();
    }

    public static void showXYZ(Coords<? extends ThreeD> c) {
        System.out.println("X, Y and Z coordinates");
        for (int i = 0; i < c.coords.length; i++)
            System.out.println(c.coords[i].x + " " + c.coords[i].y + " " + c.coords[i].z);
        System.out.println();
    }

    public static void showXYZT(Coords<? extends FourD> c) {
        System.out.println("X, Y, Z and T coordinates");
        for (int i = 0; i < c.coords.length; i++)
            System.out.println(c.coords[i].x + " " + c.coords[i].y + " " + c.coords[i].z + " " + c.coords[i].t);
        System.out.println();
    }
}
