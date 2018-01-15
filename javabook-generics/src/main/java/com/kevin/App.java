package com.kevin;

import com.kevin.coords.Coords;
import com.kevin.coords.FourD;
import com.kevin.coords.TwoD;
import com.kevin.genericInterphases.MyClass;

/**
 * Created by Kevin Kimaru Chege on 8/13/2017.
 */
public class App {
    public static void main(String[] args) {
//        intro();

//        extending();

//        coords_wildcards();

//        genericMethods();

//        genericConstructors();

        genericInterphases();

    }

    private static void genericInterphases() {
        Integer inums[] = {3, 6, 2, 8, 6};
        Character chs[] = {'b', 'r', 'p', 'w'};

        MyClass<Integer> iob = new MyClass<Integer>(inums);
        MyClass<Character> cob = new MyClass<Character>(chs);

        System.out.println("Max value in inums: " + iob.max());
        System.out.println("Min value in inums: " + iob.min());

        System.out.println("Max value in chs: " + cob.max());
        System.out.println("Min value in chs: " + cob.min());
    }

    private static void genericConstructors() {
        GenericConstructors g = new GenericConstructors(100);
        GenericConstructors g2 = new GenericConstructors(34.6F);

        g.getVal();
        g2.getVal();
    }

    private static void genericMethods() {
        Integer nums[] = {1, 4, 6, 2, 4};
        if (genericMethdods.isIn(1, nums)) {
            System.out.println("These array contains this number");
        } else {
            System.out.println("These array does not contain this number");
        }
    }

    private static void coords_wildcards() {
        TwoD td[] = {
                new TwoD(0, 0),
                new TwoD(7, 9),
                new TwoD(18, 4),
                new TwoD(-1, -23)
        };
        Coords<TwoD> tdlocs = new Coords<TwoD>(td);

        System.out.println("Contents of tdlocs.");
        Coords.showXY(tdlocs); // OK, is a TwoD
//        Coords.showXYZ(tdlocs); // Error, not a ThreeD
//        Coords.showXYZT(tdlocs); // Error, not a FourD

        FourD fd[] = {
                new FourD(1, 2, 3, 4),
                new FourD(6, 8, 14, 8),
                new FourD(22, 9, 4, 9),
                new FourD(3, -2, -23, 17)
        };
        Coords<FourD> fdlocs = new Coords<FourD>(fd);

        System.out.println("Contents of fdlocs");
        Coords.showXY(fdlocs);
        Coords.showXYZ(fdlocs);
        Coords.showXYZT(fdlocs);
    }

    private static void extending() {
        Integer inums[] = {1, 2, 3, 4, 5};
        StackExtend<Integer> iob = new StackExtend<>(inums);
        double v = iob.average();
        System.out.println("Integers average: " + v);

        Double dnums[] = {1.2, 2.4, 4.5, 2.9};
        StackExtend<Double> dOb = new StackExtend<>(dnums);
        double w = dOb.average();
        System.out.println("Doubles average " + w);

        if (iob.sameAvg(dOb)) {
            System.out.println("iob average is equal to dob average");
        } else {
            System.out.println("iob average is not equal to dob average");
        }
    }

    private static void intro() {
        // Create a GenEx reference for Integers.
        GenEx<Integer> iOb;

        // Create a Gen<Integer> object and assign its reference to iOb.
        // Notice the use of autoboxing to encapsulate the value 88 within an Integer object.
        iOb = new GenEx<>(88);

        iOb.showType();

        // Get the value in iOb. Notice that no cast is needed.
        int v = iOb.getOb();
        System.out.println(v);

        System.out.println();


        GenEx<String> strOb = new GenEx<>("Generics Text");
        strOb.showType();
        String str = strOb.getOb();
        System.out.println("Value: " + str);
    }
}
