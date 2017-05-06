package com.javarush.task.task39.task3910;

/* 
isPowerOfThree
*/
public class Solution {
    public static void main(final String[] args) {
        System.out.println(isPowerOfThree(9));
        System.out.println(isPowerOfThree(12));
        System.out.println(isPowerOfThree(0));
        System.out.println(isPowerOfThree(-9));
    }

    public static boolean isPowerOfThree(int n) {
        if (n == 0) return false;

        while (n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }
}
