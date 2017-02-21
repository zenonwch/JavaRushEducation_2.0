package com.javarush.task.task01.task0132;

/* 
Сумма цифр трехзначного числа
*/

public class Solution {
    public static void main(final String[] args) {
        System.out.println(sumDigitsInNumber(546));
    }

    public static int sumDigitsInNumber(int number) {
        int result = 0;
        while (number > 0) {
            result += number % 10;
            number /= 10;
        }
        return result;

// variant with checking number of digits in numbers
//        if (number / 1000 != 0 && number / 100 == 0)
//            throw new NumberFormatException();
//        return number / 100 + number % 100 / 10 + number % 10;
    }
}