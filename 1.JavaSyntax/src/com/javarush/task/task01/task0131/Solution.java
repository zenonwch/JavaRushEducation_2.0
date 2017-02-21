package com.javarush.task.task01.task0131;

/* 
Полнометражная картина
*/

public class Solution {
    public static void main(final String[] args) {
        System.out.println(getMetreFromCentimetre(243));
    }

    public static int getMetreFromCentimetre(final int centimetre) {
        return centimetre / 100;
    }
}