package com.javarush.task.task07.task0718;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Проверка на упорядоченность
*/
public class Solution {
    public static void main(final String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        final ArrayList<String> aList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            aList.add(reader.readLine());
        }
        int prevLength = 0;
        for (int i = 0; i < 10; i++) {
            if (aList.get(i).length() <= prevLength) {
                System.out.println(i);
                break;
            }
            prevLength = aList.get(i).length();
        }
    }
}