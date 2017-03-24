package com.javarush.task.task32.task3210;

import java.io.IOException;
import java.io.RandomAccessFile;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(final String... args) {
        final String fileName = args[0];
        final String strNumber = args[1];
        final String text = args[2];

        try (final RandomAccessFile raf = new RandomAccessFile(fileName, "rw")) {
            final int number = Integer.parseInt(strNumber);
            raf.seek(number);

            final int textSize = text.length();
            final byte[] buffer = new byte[textSize];
            raf.read(buffer, 0, textSize);

            final long fileLength = raf.length();
            raf.seek(fileLength);

            final String textFromFile = convertByteToString(buffer);
            raf.write(textFromFile.equals(text) ? "true".getBytes() : "false".getBytes());
        } catch (final IOException ignored) {
        }
    }

    private static String convertByteToString(final byte[] readBytes) {
        return new String(readBytes);
    }
}
