package com.javarush.task.task30.task3009;

import java.util.HashSet;
import java.util.Set;

/* 
Палиндром?
*/

public class Solution {
    private static Set<Integer> getRadix(final String number) {
        final Set<Integer> result = new HashSet<>();
        try {
            for (int i = 2; i <= 36; i++) {
                final String newNumber = Integer.toString(Integer.parseInt(number), i);
                final String reversNumber = new StringBuilder(newNumber).reverse().toString();
                if (newNumber.equals(reversNumber))
                    result.add(i);
            }
        } catch (final NumberFormatException ignored) {
        }
        return result;
    }

    public static void main(final String[] args) {
        System.out.println(getRadix("112"));        //expected output: [3, 27, 13, 15]
        System.out.println(getRadix("123"));        //expected output: [6]
        System.out.println(getRadix("5321"));       //expected output: []
        System.out.println(getRadix("1A"));         //expected output: []
    }
}