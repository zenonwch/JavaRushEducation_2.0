package com.javarush.task.task08.task0812;

import java.io.*;
import java.util.ArrayList;

/* 
Cамая длинная последовательность
*/
public class Solution {
    public static void main(final String[] args) throws IOException {
        final ArrayList<Integer> intList = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 10; i++) {
            final int number = Integer.parseInt(reader.readLine());
            intList.add(number);
        }
        reader.close();

        int maxSiquence = 1;
        int counter = 1;
        int prevNumber = intList.get(0);

        for (int i = 1; i < 10; i++) {
            final int currNumber = intList.get(i);
            if (currNumber == prevNumber) counter++;
            else counter = 1;
            if (counter > maxSiquence) maxSiquence = counter;
            prevNumber = currNumber;
        }

        System.out.println(maxSiquence);
    }
}