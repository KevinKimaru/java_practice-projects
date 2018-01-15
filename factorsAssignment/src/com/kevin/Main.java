package com.kevin;

public class Main {

    public static void main(String[] args) {
        int no = 1;
        int divisor = 2;

        while (divisor >= 2 && divisor <= no) {
            while (no % divisor == 0) {
                no = no / divisor;
                System.out.println(divisor);
            }
            divisor++;
        }
    }
}
