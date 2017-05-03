package com.javarush.task.task39.task3903;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Неравноценный обмен
*/
public class Solution {
    public static void main(final String[] args) throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Please type in a number: ");

        final long number = Long.parseLong(reader.readLine());
        System.out.println("Please type in first index: ");
        final int i = Integer.parseInt(reader.readLine());
        System.out.println("Please type in second index: ");
        final int j = Integer.parseInt(reader.readLine());

        System.out.println("The result of swapping bits is " + swapBits(number, i, j));
    }

    //positions are indexed from 0 and in order   ...[4][3][2][1][0]
    //so changing 3 and 1 will make                     ...[4][1][2][3][0]
    public static long swapBits(final long number, final int i, final int j) {

        final long bit1 = (number >> i) & 1;// bit at pos1
        final long bit2 = (number >> j) & 1;// bit at pos2

        if (bit1 == bit2) return number; // no need to swap since we change 1 with 1 or 0 with 0

        // Since we are here it means that we need to change 1->0 and 0->1.
        // To do this we can use XOR (^).
        // Lets create mask 000001010 with ones at specified positions
        final long mask = (1L << i) | (1L << j);

        return number ^ mask;
    }
}
