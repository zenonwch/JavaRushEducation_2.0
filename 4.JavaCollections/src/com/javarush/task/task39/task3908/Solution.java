package com.javarush.task.task39.task3908;

import java.util.HashMap;
import java.util.Map;

/*
Возможен ли палиндром?
*/
public class Solution {
    public static void main(final String[] args) {
        System.out.println(isPalindromePermutation(";;ab\\c7b\\c7"));
    }

    public static boolean isPalindromePermutation(final String s) {
        if (s == null || s.isEmpty()) return false;

        final Map<Character, Integer> charOccurrences = new HashMap<>();
        for (final char ch : s.toLowerCase().toCharArray()) {
            final Integer count = charOccurrences.get(ch);
            charOccurrences.put(ch, (count == null) ? 1 : count + 1);
        }

        int oddCharNumberCounter = 0;
        for (final Map.Entry<Character, Integer> pair : charOccurrences.entrySet()) {
            if (s.length() % 2 == 0 && pair.getValue() % 2 != 0) return false;

            if (pair.getValue() % 2 != 0) {
                if (s.length() % 2 != 0 && oddCharNumberCounter > 1) {
                    return false;
                }
                oddCharNumberCounter++;
            }
        }

        return !(s.length() % 2 != 0 && oddCharNumberCounter != 1);
    }
}
