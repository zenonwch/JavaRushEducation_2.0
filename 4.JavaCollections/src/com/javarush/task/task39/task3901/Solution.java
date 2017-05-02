package com.javarush.task.task39.task3901;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/* 
Уникальные подстроки
*/
public class Solution {
    public static void main(final String[] args) throws IOException {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please input your string: ");
        final String s = bufferedReader.readLine();

        System.out.println("The longest unique substring length is: \n" + lengthOfLongestUniqueSubstring(s));
    }

    public static int lengthOfLongestUniqueSubstring(final String s) {
        if (s == null || s.isEmpty()) return 0;

        int maxSize = 0;

        final Set<Character> symbols = new HashSet<>();
        for (final char ch : s.toCharArray()) {
            if (symbols.contains(ch)) {
                symbols.clear();
            }
            symbols.add(ch);
            maxSize = symbols.size() > maxSize ? symbols.size() : maxSize;
        }

        return maxSize;
    }
}
