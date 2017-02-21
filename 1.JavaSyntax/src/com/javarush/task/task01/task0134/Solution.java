package com.javarush.task.task01.task0134;

/* 
Набираем воду в бассейн
*/

public class Solution {
    public static void main(final String[] args) {
        System.out.println(getVolume(25, 5, 2));
    }

    public static long getVolume(final int a, final int b, final int c) {
        return 1000L * a * b * c;
    }
}