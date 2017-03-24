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
        System.out.println(decode(reader3, 3));  //Hello Amigo
        final StringReader reader4 = new StringReader("Khoor Dpljr");
        System.out.println(decode(reader4, -29));  //Hello Amigo
        final StringReader reader5 = new StringReader("Khoor Dpljr");
        System.out.println(decode(reader5, 49));  //Hello Amigo
    }

    public static String decode(final StringReader reader, final int key) throws IOException {
        if (reader == null)
            return "";

        final StringBuilder sb = new StringBuilder("");
        int c;
        while ((c = reader.read()) != -1) {
            if (c > 64 && c < 91) {
                c += key % 26;
                if (c < 65) c += 26;
                if (c > 90) c -= 26;
            }
            if (c > 96 && c < 123) {
                c += key % 26;
                if (c < 97) c += 26;
                if (c > 122) c -= 26;
            }
            sb.append((char) c);
        }

        return sb.toString();
    }
}
