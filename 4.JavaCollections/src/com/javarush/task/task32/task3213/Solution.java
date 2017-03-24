package com.javarush.task.task32.task3213;

import java.io.IOException;
import java.io.StringReader;

/* 
Шифр Цезаря
*/

public class Solution {
    public static void main(final String[] args) throws IOException {
        final StringReader reader = new StringReader("Khoor Dpljr");
        System.out.println(decode(reader, -3));  //Hello Amigo
        final StringReader reader2 = new StringReader("A a");
        System.out.println(decode(reader2, -3));  //Hello Amigo
        final StringReader reader3 = new StringReader("Z z");
        System.out.println(decode(reader3, 5));  //Hello Amigo

    }

    public static String decode(final StringReader reader, final int key) throws IOException {
        if (reader == null)
            return "";

        final StringBuilder sb = new StringBuilder("");
        int c;
        while ((c = reader.read()) != -1) {
            c += key;
            sb.append((char) c);
        }

        return sb.toString();
    }
}
