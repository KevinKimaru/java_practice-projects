package com.kevin;

import java.util.Scanner;

public class Main {

    private static int vehiclesPassed = 0;
    private static int vehiclesBelow2 = 0;
    private static int amountPaid = 0;
    private static float weight;
    private static String sep = "=================================";
    private static String errSep = "+++++++++++++++++++++++++++";

    private static String closeJob = "";

    public static void main(String[] args) {
        toolBoothSimulation();
    }

    private static void displayData () {
        System.out.println("\n\n" + sep);
        System.out.println("Vehicles Passed: " + vehiclesPassed);
        System.out.println("Vehicles paid: " + (vehiclesPassed - vehiclesBelow2));
        System.out.println("Vehicles which did not pay: " + vehiclesBelow2);
        System.out.println("Amount Lost: " + "ambiguous");
        System.out.println("Current total amount collected: " + amountPaid);
        System.out.println(sep + "\n\n");
    }

    private static void performCalculations(String input) {
        try {
            weight = Float.parseFloat(input);

            if (weight <= 0) {
                System.out.println("\n\n" + errSep);
                System.out.println("Error:->-> Vehicle weight cannot be zero or less than zero.");
                System.out.println(errSep + "\n\n");
            } else if (weight < 2) {
                vehiclesPassed++;
                vehiclesBelow2++;
                amountPaid+=0;
            } else if (weight > 2 && weight <= 5) {
                vehiclesPassed++;
                amountPaid+=100;
            } else if (weight > 5 && weight <= 8) {
                vehiclesPassed++;
                amountPaid+=150;
            } else if (weight > 8 && weight <= 12) {
                vehiclesPassed++;
                amountPaid+=230;
            } else if (weight > 12 && weight <= 20) {
                vehiclesPassed++;
                amountPaid+=500;
            } else if (weight > 20) {
                vehiclesPassed++;
                amountPaid+=1000;
            }
        } catch (Exception e) {
            System.out.println("\n\n" + errSep);
            System.out.println("Error:->->Please enter a number.");
            System.out.println(errSep + "\n\n");
        }
    }

    private static void toolBoothSimulation() {
        Scanner sc = new Scanner(System.in);
        System.out.println(sep);
        System.out.println("Options of the input either be 'show' to show current data or 'close' to close program.");

        while(!closeJob.contentEquals("close")) {
            System.out.println("Enter option or weight of the vehicle: ");
            String  input = sc.nextLine();
            if (input.compareToIgnoreCase("show") == 0) {
                displayData();
            } else if(input.compareToIgnoreCase("close") == 0) {
                displayData();
                break;
            } else {
                performCalculations(input);
            }
            System.out.println("\n"+ sep);
        }
    }
}
