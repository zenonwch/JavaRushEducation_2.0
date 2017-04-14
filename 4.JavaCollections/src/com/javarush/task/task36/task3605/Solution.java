package com.javarush.task.task36.task3605;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/* 
Использование TreeSet
*/
public class Solution {
    public static void main(final String[] args) throws IOException {
        final String fileName = args[0];
        if (fileName == null) return;

        final Set<Character> letters = new TreeSet<>();
        final BufferedReader reader = new BufferedReader(new FileReader(fileName));
        int symbol;
        while ((symbol = reader.read()) != -1) {
            final char ch = (char) symbol;
            if (Character.isLetter(ch)) {
                letters.add(Character.toLowerCase(ch));
            }
        }
        reader.close();

        final Iterator<Character> iterator = letters.iterator();
        int i = 5;
        while (iterator.hasNext() && i > 0) {
            System.out.print(iterator.next());
            i--;
        }
    }
}
