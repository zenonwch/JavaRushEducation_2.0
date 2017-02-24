package com.javarush.task.task06.task0606;

import java.io.*;

/* 
Чётные и нечётные циферки
*/

public class Solution {

    public static int even;
    public static int odd;

    public static void main(final String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        final String s = reader.readLine();
        int num = Integer.parseInt(s);
        while (num > 0) {
            if (num % 2 == 0) even++;
            else odd++;
            num /= 10;
        }
        System.out.println("Even: " + even + " Odd: " + odd);
    }
}
