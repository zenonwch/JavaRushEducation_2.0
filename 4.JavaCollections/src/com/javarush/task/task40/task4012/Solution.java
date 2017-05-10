package com.javarush.task.task40.task4012;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

/* 
Полезные методы DateTime API
*/

public class Solution {
    public static void main(final String[] args) {
        System.out.println(isLeap(LocalDate.of(2016, 1, 1)));
        System.out.println(isLeap(LocalDate.of(2000, 1, 1)));

        System.out.println(isBefore(LocalDateTime.of(LocalDate.of(2017, 5, 11), LocalTime.now())));
        System.out.println(isBefore(LocalDateTime.of(LocalDate.of(2017, 1, 11), LocalTime.now())));

        System.out.println(addTime(LocalTime.of(10, 0, 0), 30, ChronoUnit.MINUTES));

        final LocalDate firstDate = LocalDate.of(2017, 5, 10);
        final LocalDate secondDate = LocalDate.of(2017, 5, 12);
        System.out.println(getPeriodBetween(firstDate, secondDate).getDays());
        System.out.println(getPeriodBetween(secondDate, firstDate));
    }

    public static boolean isLeap(final LocalDate date) {
        return date.isLeapYear();
    }

    public static boolean isBefore(final LocalDateTime dateTime) {
        return dateTime.isBefore(LocalDateTime.now());
    }

    public static LocalTime addTime(final LocalTime time, final int n, final ChronoUnit chronoUnit) {
        return time.plus(n, chronoUnit);
    }

    public static Period getPeriodBetween(final LocalDate firstDate, final LocalDate secondDate) {
        return firstDate.isBefore(secondDate) ? Period.between(firstDate, secondDate) : Period.between(secondDate, firstDate);
    }
}
