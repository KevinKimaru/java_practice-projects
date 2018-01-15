package com.kevin;

import com.kevin.question.AskMe;
import com.kevin.question.Question;

/**
 * Created by Kevin Kimaru Chege on 8/12/2017.
 */
public class App {
    public static void main(String[] args) {
//        intro();
//        value_valueof();

//        advancedEnums();
//        ord_compto_equa();

        questions();

    }

    private static void questions() {
        Question question = new Question();
        AskMe.answer(question.ask());
        AskMe.answer(question.ask());
        AskMe.answer(question.ask());
    }

    private static void ord_compto_equa() {
        Apple ap, ap2, ap3;

        // Obtain all ordinal values using ordinal().
        System.out.println("All apple constants and their ordinal values.");
        for (Apple a: Apple.values()) System.out.println(a + " " + a.ordinal());

        ap = Apple.RedBel;
        ap2 = Apple.GoldenBel;
        ap3 = Apple.RedBel;

        System.out.println();

        //Demonstrate compareTo() and equals()
        if (ap.compareTo(ap2) < 0) System.out.println(ap + " comes before " + ap2);
        if (ap.compareTo(ap2) > 0) System.out.println(ap2 + " comes before " + ap);
        if (ap.compareTo(ap3) == 0) System.out.println(ap + " equals " + ap2);

        System.out.println();

        if (ap.equals(ap2)) System.out.println("Error!");
        if (ap.equals(ap3)) System.out.println(ap + " equals " + ap3);
        if (ap == ap3) System.out.println(ap + "==" + ap3);
    }

    private static void advancedEnums() {
        AppleAdv ap;

        //display price of winesap
        System.out.println("Winesap costs: " + AppleAdv.Winesap.getPrice() + "cents.\n");

        //display all apples and prices
        System.out.println("All apple prices:");
        for (AppleAdv a: AppleAdv.values()) System.out.println(a + " costs " + a.getPrice() + " cents.");
    }

    private static void value_valueof() {
        Apple ap;

        System.out.println("Here are all Apple constants");
        for (Apple a: Apple.values()) System.out.println(a);
        System.out.println();

        //valueOf( ) returns the enumeration value associated with the name of the constant represented as a string.
        ap = Apple.valueOf("Winesap");
        System.out.println("ap contains " + ap);
    }

    private static void intro() {
        Apple ap;

        ap = Apple.RedBel;

        //output an enum value
        System.out.println("Value of ap: " + ap + "\n");

        ap = Apple.GoldenBel;

        //compare two enum values
        if (ap == Apple.GoldenBel)
            System.out.println("Apple contains GoldenBel.\n");

        //use an enum to control switch statement
        switch (ap) {
            case Jonathan:
                System.out.println("Jonathan is red.");
                break;
            case RedBel:
                System.out.println("Red Delicious is red.");
                break;
            case Winesap:
                System.out.println("Winesap is red.");
                break;
            case Cortland:
                System.out.println("Cortland is red.");
                break;
            case GoldenBel:
                System.out.println("Golden Delicious is yellow.");
                break;
        }
    }
}
