package com.kevin;

import com.kevin.max.MyClass;
import com.kevin.max.UseMethodRef;
import com.kevin.moreon_method_references.GenericMethodRefDemo;
import com.kevin.moreon_method_references.MyArrayOps;
import com.kevin.simple_factorial.MyFunc;
import com.kevin.temp.HighTemp;
import com.kevin.temp.InsideMethWithObjRefDemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Function;

import static com.kevin.simple_factorial.ConstructorRefDemo3.myClassFactory;

/**
 * Created by Kevin Kimaru Chege on 8/14/2017.
 */

interface MyNumber {
    double getValue();
}

interface NumericTest {
    boolean test(int n);
}

interface NumericTest2 {
    boolean test(int n, int d);
}

interface NumericFunc {
    int func(int n);
}

interface StringFunc {
    String func(String n);
}

interface SomeFunc<T> {
    T func(T t);
}

interface DoubleNumericArrayFunction {
    double func(double[] n) throws EmptyArrayException;
}

class EmptyArrayException extends Exception {
    EmptyArrayException() {
        super("Empty Array");
    }
}

class MyStringOps {
    String strReverse(String str) {
        String result = "";
        int i;

        for (i = str.length() - 1; i >= 0; i--) result += str.charAt(i);

        return result;
    }
}

interface myFunc {
    MyClass2 func(int n);
}

class MyClass2 {
    private int val;

    MyClass2(int v) {
        val = v;
    }

    MyClass2() {
        val = 0;
    }

    int getVal() {
        return val;
    }
}

interface MyFunc2<T> {
    MyClass3<T> func(T n);
}

class MyClass3<T> {
    private T val;

    MyClass3(T v) {
        val = v;
    }

    MyClass3() {
        val = null;
    }

    T getVal() {
        return val;
    }

}

interface MyArrayCreator<T> {
    T func(int n);
}


public class App {
    public static void main(String[] args) throws EmptyArrayException {
//        intro();

//        intromethodwithparams();
//        introWithMethodparams2();

//        blocklambdasfactorial();

//        blocklambdasreverse();
//        usingGenerics();

//        lambda_expressions_as_arguments();

//        lamdas_and_exception();

//        using_method_references();

//        more_on_method_references();

//        method_references_with_generics();

//        application_in_max();
//        constructor_references();
//        constructor_references_with_generics();
//        simple_factory();
//        more_on_constructor_references();

    }

    private static void more_on_constructor_references() {
        MyArrayCreator<com.kevin.simple_factorial.MyClass[]> mcArrayCons = com.kevin.simple_factorial.MyClass[]::new;
        com.kevin.simple_factorial.MyClass[] a = mcArrayCons.func(2);
        a[0] = new com.kevin.simple_factorial.MyClass(1);
        a[1] = new com.kevin.simple_factorial.MyClass(2);

    }

    private static void simple_factory() {
        // Create a reference to a MyClass constructor.
        // In this case, new refers to the constructor that
        // takes an argument.
        MyFunc<com.kevin.simple_factorial.MyClass<Double>, Double> myClassCons =
                com.kevin.simple_factorial.MyClass<Double>::new;

        // Create an instance of MyClass by use of the factory method.
        com.kevin.simple_factorial.MyClass<Double> mc = myClassFactory(myClassCons, 100.1);

        // Use the instance of MyClass just created.
        System.out.println("val in mc is " + mc.getVal());

        // Now, create a different class by use of myClassFactory().
        MyFunc<com.kevin.simple_factorial.MyClass2, String> myClassCons2 = com.kevin.simple_factorial.MyClass2::new;

        // Create an instance of MyClass2 by use of the factory method.
        com.kevin.simple_factorial.MyClass2 mc2 = myClassFactory(myClassCons2, "Lambda");

        // Use the instance of MyClass just created.
        System.out.println("str in mc2 is " + mc2.getVal());

    }

    private static void using_function() {
        Function<Integer, Integer> factorial = (n) -> {
            int result = 1;
            for (int i = 1; i <= n; i++) result = i * result;
            return result;
        };

        System.out.println("The factoral of 3 is " + factorial.apply(3));
        System.out.println("The factoral of 5 is " + factorial.apply(5));
    }

    private static void constructor_references_with_generics() {
        // Create a reference to the MyClass<T> constructor.
        MyFunc2<Integer> myClassCons = MyClass3<Integer>::new;

        // Create an instance of MyClass<T> via that constructor reference.
        MyClass3<Integer> mc = myClassCons.func(100);

        // Use the instance of MyClass<T> just created.
        System.out.println("val in mc is " + mc.getVal());
    }

    private static void constructor_references() {
        // Create a reference to the MyClass constructor.
        // Because func() in MyFunc takes an argument, new
        // refers to the parameterized constructor in MyClass,
        // not the default constructor.
        myFunc myClassCons = MyClass2::new;

        MyClass2 mc = myClassCons.func(100);

        // Use the instance of MyClass just created.
        System.out.println("val in mc is " + mc.getVal());
    }

    private static void application_in_max() {
        ArrayList<MyClass> al = new ArrayList<MyClass>();

        al.add(new MyClass(1));
        al.add(new MyClass(4));
        al.add(new MyClass(2));
        al.add(new MyClass(9));
        al.add(new MyClass(3));
        al.add(new MyClass(7));

        // Find the maximum value in al using the compareMC() method.

        MyClass maxValObj = Collections.max(al, UseMethodRef::compareMC);

        System.out.println("Maximum value is: " + maxValObj.getVal());
    }

    private static void method_references_with_generics() {
        Integer[] vals = {1, 2, 3, 4, 2, 3, 4, 4, 5};
        String[] strs = {"One", "Two", "Three", "Two"};
        int count;

        count = GenericMethodRefDemo.myOp(MyArrayOps::<Integer>countMaching, vals, 4);
        System.out.println("vals contains " + count + " 4s");

        count = GenericMethodRefDemo.myOp(MyArrayOps::<String>countMaching, strs, "Two");
        System.out.println("strs contains " + count + " Twos");
    }

    private static void more_on_method_references() {
        int count;

        // Create an array of HighTemp objects.
        HighTemp[] weekDayHighs = {
                new HighTemp(89), new HighTemp(82),
                new HighTemp(90), new HighTemp(89),
                new HighTemp(89), new HighTemp(91),
                new HighTemp(84), new HighTemp(83)
        };
        // Use counter() with arrays of the class HighTemp.
        // Notice that a reference to the instance method
        // sameTemp() is passed as the second argument.
        count = InsideMethWithObjRefDemo.counter(weekDayHighs, HighTemp::sameTemp, new HighTemp(89));
        System.out.println(count + " days had a high of 89");

        // Now, create and use another array of HighTemp objects.
        HighTemp[] weekDayHighs2 = {
                new HighTemp(32), new HighTemp(12),
                new HighTemp(24), new HighTemp(19),
                new HighTemp(18), new HighTemp(12),
                new HighTemp(-1), new HighTemp(13)
        };
        count = InsideMethWithObjRefDemo.counter(weekDayHighs2, HighTemp::sameTemp, new HighTemp(12));
        System.out.println(count + " days had a high of 12");

        // Now, use lessThanTemp() to find days when temperature was less than a specified value.
        count = InsideMethWithObjRefDemo.counter(weekDayHighs, HighTemp::lessThanTemp, new HighTemp(89));
        System.out.println(count + " days had a high less than 89");

        count = InsideMethWithObjRefDemo.counter(weekDayHighs2, HighTemp::lessThanTemp, new HighTemp(19));
        System.out.println(count + " days had a high of less than 19");
    }

    private static void using_method_references() {
        String inStr = "Lambdas add power to Java";
        String outStr;

        // Create a MyStringOps object.
        MyStringOps strOps = new MyStringOps();
        outStr = stringOpp(strOps::strReverse, inStr);

        System.out.println("Original string: " + inStr);
        System.out.println("Reversed string: " + outStr);
    }

    private static String stringOpp(StringFunc sf, String s) {
        return sf.func(s);
    }

    private static void lamdas_and_exception() throws EmptyArrayException {
        double[] values = {1.2, 3.4, 3.2, 7.8, 9.0};
        DoubleNumericArrayFunction average = (n) -> {
            double sum = 0;

            if (n.length == 0) throw new EmptyArrayException();

            for (int i = 0; i < n.length; i++) sum += n[i];
            return sum / n.length;
        };
        System.out.println(average.func(values));
        System.out.println(average.func(new double[0]));
    }

    private static void lambda_expressions_as_arguments() {
        String inStr = "Lambdas add power to Java";
        String outStr = stringOp((str) -> str.toUpperCase(), inStr);
        System.out.println(outStr);

        // This passes a block lambda that removes spaces.
        outStr = stringOp((str) -> {
            String result = "";
            int i;

            for (i = 0; i < str.length(); i++) if (str.charAt(i) != ' ') result += str.charAt(i);

            return result;
        }, inStr);
        System.out.println(outStr);

        // Of course, it is also possible to pass a StringFunc instance
        // created by an earlier lambda expression. For example,
        // after this declaration executes, reverse refers to an
        // instance of StringFunc.
        StringFunc reverse = (str) -> {
            String result = "";
            int i;

            for (i = str.length() - 1; i >= 0; i--) result += str.charAt(i);

            return result;
        };
        // Now, reverse can be passed as the first parameter to stringOp() since it refers to a StringFunc object.
        System.out.println("The string reversed: " + stringOp(reverse, inStr));
    }

    // This method has a functional interface as the type of
    // its first parameter. Thus, it can be passed a reference to
    // any instance of that interface, including the instance created   // by a lambda expression.
    // The second parameter specifies the string to operate on.
    private static String stringOp(StringFunc sf, String s) {
        return sf.func(s);
    }

    private static void usingGenerics() {
        SomeFunc<String> reverse = (str) -> {
            String result = "";
            for (int i = str.length() - 1; i > 0; i--) result += str.charAt(i);
            return result;
        };

        System.out.println("Lambda reversed is: " + reverse.func("Lambda"));
        System.out.println("Expression reversed is: " + reverse.func("Expression"));

        SomeFunc<Integer> factorial = n -> {
            int result = 1;
            for (int i = 1; i <= n; i++) {
                result = i * result;
            }
            return result;
        };

        System.out.println("The factorial of 5 is: " + factorial.func(5));
        System.out.println("The factorial of 3 is: " + factorial.func(3));
    }

    private static void blocklambdasreverse() {
        StringFunc reverse = (str) -> {
            String result = "";
            for (int i = str.length() - 1; i > 0; i--) result += str.charAt(i);
            return result;
        };

        System.out.println("Lambda reversed is: " + reverse.func("Lambda"));
        System.out.println("Expression reversed is: " + reverse.func("Expression"));
    }

    private static void blocklambdasfactorial() {
        NumericFunc factorial = n -> {
            int result = 1;
            for (int i = 1; i <= n; i++) {
                result = i * result;
            }
            return result;
        };

        System.out.println("The factorial of 5 is: " + factorial.func(5));
        System.out.println("The factorial of 3 is: " + factorial.func(3));
    }

    private static void introWithMethodparams2() {
        // This lambda expression determines if one number is  a factor of another.
        NumericTest2 isFactor = (n, d) -> (n % d) == 0;

        if (isFactor.test(10, 2)) System.out.println("2 is a factor of 10");
        if (!isFactor.test(10, 3)) System.out.println("3 is not a factor of 10");
    }

    private static void intromethodwithparams() {
        NumericTest isEven = n -> (n % 2) == 0;
        if (isEven.test(10)) System.out.println("10 is even");
        if (!isEven.test(9)) System.out.println("9 is not even");

        NumericTest isNotNeg = n -> (n > 0);
        if (isNotNeg.test(7)) System.out.println("7 is not negative");
        if (!isNotNeg.test(-7)) System.out.println("-7 is negative");
    }

    private static void intro() {
        MyNumber myNum;  // declare an interface reference

        // Here, the lambda expression is simply a constant expression.
        // When it is assigned to myNum, a class instance is  constructed in which the lambda expression implements
        // the getValue() method in MyNumber.
        myNum = () -> Math.random() * 100;

        System.out.println("Random :" + myNum.getValue());
        System.out.println("Random 2 :" + myNum.getValue());

        // A lambda expression must be compatible with the method
        // defined by the functional interface. Therefore, this won't work:
        //  myNum = () -> "123.03"; // Error!
    }

}
