package com.javarush.task.task37.task3714;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Древний Рим
*/
public class Solution {
    public static void main(final String[] args) throws IOException {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input a roman number to be converted to decimal: ");
        final String romanString = bufferedReader.readLine();
        System.out.println("Conversion result equals " + romanToInteger(romanString));
    }

    public static int romanToInteger(final String s) {
        int previous = Integer.MAX_VALUE;
        int sum = 0;
        int sameCharSequenceCounter = 0;
        boolean alowedNextSubtraction = false;
        final String nonRepeatable = "VLD";

        for (final char ch : s.toCharArray()) {
            final int current = convertDigit(ch);

            if (current < previous) {
                sum += current;
                sameCharSequenceCounter = 0;
                alowedNextSubtraction = true;
            } else if (current == previous && nonRepeatable.indexOf(ch) == -1 && sameCharSequenceCounter < 3) {
                sum += current;
                sameCharSequenceCounter++;
                alowedNextSubtraction = true;
            } else if (current > previous && alowedNextSubtraction
                    && (current / previous == 5 || current / previous == 10)) {
                sum += current - previous * 2;
                sameCharSequenceCounter = 0;
                alowedNextSubtraction = false;
            } else {
                throw new NumberFormatException();
            }

            previous = current;
        }
        return sum;
    }

    private static int convertDigit(final char ch) {
        switch (ch) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                throw new NumberFormatException();
        }
    }
}
