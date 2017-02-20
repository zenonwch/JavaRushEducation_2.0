package com.javarush.task.task01.task0129;

/* 
Считаем длину окружности
*/

public class Solution {
    public static void main(final String[] args) {
        printCircleLength(5);
    }

    public static void printCircleLength(final int radius) {
        final double L = 2 * 3.14 * radius;
        System.out.println(L);
    }
}