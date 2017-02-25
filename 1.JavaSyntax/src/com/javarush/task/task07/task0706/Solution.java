package com.javarush.task.task07.task0706;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Улицы и дома
*/

public class Solution {
    public static void main(final String[] args) throws Exception {
        final int[] array = new int[15];
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int odd = 0;
        int even = 0;
        for (int i = 0, l = array.length; i < l; i++) {
            array[i] = Integer.parseInt(reader.readLine());
            if (i % 2 == 0) even += array[i];
            else odd += array[i];
        }
        reader.close();
        System.out.println(odd > even
                ? "В домах с нечетными номерами проживает больше жителей."
                : "В домах с четными номерами проживает больше жителей.");
    }
}
