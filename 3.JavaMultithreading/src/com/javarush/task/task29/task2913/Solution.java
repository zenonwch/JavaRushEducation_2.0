package com.javarush.task.task29.task2913;

import java.util.Random;

/* 
Замена рекурсии
*/

public class Solution {
    private static int numberA;
    private static int numberB;

    public static String getAllNumbersBetween(final int a, final int b) {
        final int direction = a < b ? 1 : -1;
        int nextStep = a;
        final StringBuilder sb = new StringBuilder("");

        while (nextStep != b) {
            sb.append(nextStep).append(' ');
            nextStep += direction;
        }

        return sb.append(nextStep).toString();
    }

    public static void main(final String[] args) {
        final Random random = new Random();
        numberA = random.nextInt() % 1_000;
        numberB = random.nextInt() % 10_000;
        System.out.println(getAllNumbersBetween(numberA, numberB));
        System.out.println(getAllNumbersBetween(numberB, numberA));
    }
}