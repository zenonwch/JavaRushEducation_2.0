package com.javarush.task.task07.task0712;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Самые-самые
*/

public class Solution {
    public static void main(final String[] args) throws IOException {
        final ArrayList<String> aList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int indShort = 0;
        int indLong = 0;
        for (int i = 0; i < 10; i++) {
            final String string = reader.readLine();
            aList.add(string);
            if (aList.get(i).length() < aList.get(indShort).length()) indShort = i;
            if (aList.get(i).length() > aList.get(indLong).length()) indLong = i;
        }
        reader.close();
        System.out.println(aList.get(indShort < indLong ? indShort : indLong));
    }
}
