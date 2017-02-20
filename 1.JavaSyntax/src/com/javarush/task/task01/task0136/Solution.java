package com.javarush.task.task01.task0136;

/* 
Да хоть на Луну!
*/

public class Solution {
    public static void main(final String[] args) {
        System.out.println(getWeight(888));
    }

    public static double getWeight(final int weightEarth) {
        return 0.17 * weightEarth;
    }
}