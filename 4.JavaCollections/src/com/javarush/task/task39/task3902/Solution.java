package com.javarush.task.task39.task3902;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Биты были биты
*/
public class Solution {
    public static void main(final String[] args) throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Please type in a number: ");

        final long l = Long.parseLong(reader.readLine());
        final String result = isWeightEven(l) ? "even" : "odd";
        System.out.println("Number of ones in a given number is " + result);

    }

    public static boolean isWeightEven(final long number) {
        return Long.bitCount(number) %2 == 0;
    }
}
