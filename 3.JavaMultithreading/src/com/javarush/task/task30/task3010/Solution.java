package com.javarush.task.task30.task3010;

/* 
Минимальное допустимое основание системы счисления
*/

import java.math.BigInteger;
import java.util.regex.Pattern;

public class Solution {
    private static final Pattern COMPILE = Pattern.compile("^[a-zA-Z0-9]*$");

    public static void main(final String[] args) {
        try {
            if (!COMPILE.matcher(args[0]).matches()) {
                System.out.println("incorrect");
            } else {
                for (int i = 2; i <= 36; i++) {
                    try {
                        new BigInteger(args[0], i);
                        System.out.println(i);
                        break;
                    } catch (final NumberFormatException ignore) {
                    }
                }
            }
        } catch (final Exception ignore) {
        }
    }
}