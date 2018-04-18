package com.kevin;

public class Main {

    public static void main(String[] args) {

        int count2 = 1;
        for(int i = 0; i < 4; i++) {
            for(int j = 1; j <= count2; j++) {
                System.out.print("*");
            }
            count2++;
            System.out.println();
        }
        int count = 5;
        for(int i = 0; i < 5; i++) {
            for(int j = 1; j <= count; j++) {
                System.out.print("*");
            }
            count--;
            System.out.println();
        }
    }
}
