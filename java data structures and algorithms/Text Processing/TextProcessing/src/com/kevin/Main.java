package com.kevin;

public class Main {

    public static void main(String[] args) {
        System.out.println(bruteForceMatch("Kevin", "nil"));
        int l = Math.min(4, 6);
    }

    private static int bruteForceMatch(String t, String p) {
        int n = t.length();
        int m = p.length();
        for (int i = 0; i < n - m; i++) {
            int j = 0;
            int k = i;
            while (j < m && t.charAt(k) == p.charAt(j)) {
                k++;
                j++;
            }
            if (j == m) {
                return i;
            }
        }
        return -1;
    }
}
