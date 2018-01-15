package com.kevin;

public class Main {

    public static void main(String[] args) {
        drawCalendar();
    }

    private static void drawCalendar() {
        int year = 2017;
        int month = 11;
        int days = 31;
        int dayOfMonth = 17;
        int dayOfWeek = 6;

        System.out.print("| S | M | T | W | T | F | S |");
        while(year == 2018) {
            System.out.println("YEAR: " + year);
            System.out.print("| S | M | T | W | T | F | S |\n");
            while(month <= 12) {
                while (dayOfMonth <= days) {
                    while (dayOfWeek <= 7) {
                        System.out.printf(" %d ", days);
                        dayOfWeek++;
                    }
                    System.out.println("");
                    dayOfMonth++;
                }
                month++;
            }
        }

        switch(month) {
            case 1: days = 31;break;
            case 2: days = year % 4 == 0 ? 29 : 28; break;
            case 3: days = 31;break;
            case 4: days = 30;break;
            case 5: days = 31;break;
            case 6: days = 30;break;
            case 7: days = 31;break;
            case 8: days = 31;break;
            case 9: days = 30;break;
            case 10: days = 31;break;
            case 11: days = 30;break;
            case 12: days = 31;break;
        }
    }
}
