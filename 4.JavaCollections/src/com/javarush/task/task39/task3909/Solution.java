package com.javarush.task.task39.task3909;

import java.util.stream.IntStream;

/*
Одно изменение
*/
public class Solution {
    public static void main(final String[] args) {

    }

    public static boolean isOneEditAway(final String first, final String second) {
        if (first == null || second == null || first.length() > second.length() + 1 || first.length() < second.length() - 1)
            return false;

        if (first.equals(second)) return true;

        final char[] firstArr = first.toCharArray();
        final char[] secondArr = second.toCharArray();
        final int minLength = Math.min(firstArr.length, secondArr.length);
        final int maxLength = Math.max(firstArr.length, secondArr.length);

        int prefixIndex = -1;

        prefixIndex = IntStream
                .range(0, minLength)
                .filter(i -> firstArr[i] != secondArr[i])
                .findFirst()
                .orElse(prefixIndex);

        int postfixIndex = -1;
        for (int i = 1; i <= minLength; i++) {
            if (firstArr[firstArr.length - i] != secondArr[secondArr.length - i]) {
                postfixIndex = maxLength - i;
                break;
            }
        }

        return firstArr.length == secondArr.length && postfixIndex == prefixIndex
                || prefixIndex == -1 && postfixIndex == maxLength - 1
                || postfixIndex == -1 && prefixIndex == 0
                || postfixIndex == prefixIndex;
    }
}
