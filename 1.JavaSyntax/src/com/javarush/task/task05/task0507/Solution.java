package com.javarush.task.task05.task0507;

/* 
Среднее арифметическое
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(final String[] args) throws Exception {
        final BufferedReader reader  = new BufferedReader(new InputStreamReader(System.in));
        int sum = 0;
        int counter = 0;
        while (true) {
            final int number =  Integer.parseInt(reader.readLine());
            if (number == -1) break;
            sum += number;
            counter++;
        }
        System.out.println((double)sum/counter);
    }
}
