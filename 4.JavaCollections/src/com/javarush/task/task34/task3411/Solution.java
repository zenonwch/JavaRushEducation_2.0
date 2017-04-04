package com.javarush.task.task34.task3411;

/* 
Ханойские башни
*/

public class Solution {
    public static void main(final String[] args) {
        final int count = 5;
        moveRing('A', 'B', 'C', count);
    }

    public static void moveRing(final char a, final char b, final char c, final int count) {
        if (count == 0) return;

        moveRing(a, c, b, count - 1);
        System.out.println("from " + a + " to " + b);
        moveRing(c, b, a, count - 1);
    }
}