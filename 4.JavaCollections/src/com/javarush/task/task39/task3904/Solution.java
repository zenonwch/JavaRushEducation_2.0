package com.javarush.task.task39.task3904;

import java.util.Arrays;

/*
Лестница
*/
public class Solution {
        private static int n = 70;

    public static void main(final String[] args) {
        System.out.println("Number of possible runups for " + n + " stairs is: " + countPossibleRunups(n));
    }

    public static long countPossibleRunups(final int n) {
        if (n < 0) return 0L;
        if (n < 2) return 1L;

        long a = 1L;
        long b = 1L;
        long c = 2L;
        long d = 0L;
        for (int i = 3; i <= n; i++) {
            d = a + b + c;
            a = b;
            b = c;
            c = d;
        }

        return d;
    }
}

