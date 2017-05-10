package com.javarush.task.task40.task4008;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/*
Работа с Java 8 DateTime API
*/

public class Solution {
    public static void main(final String[] args) {
        printDate("21.4.2014 15:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");
    }

    public static void printDate(final String date) {
        final String[] formats = {"d.M.u", "H:m:s"};

        final String time = date.split(" ").length == 1 ? date : date.split(" ")[1];

        for (final String format : formats) {
            try {
                final LocalDate localDate = LocalDate.parse(date.split(" ")[0], DateTimeFormatter.ofPattern(format));

                System.out.println("День: " + localDate.getDayOfMonth());
                System.out.println("День недели: " + localDate.getDayOfWeek().getValue());
                System.out.println("День месяца: " + localDate.getDayOfMonth());
                System.out.println("День года: " + localDate.getDayOfYear());
                System.out.println("Неделя месяца: " + localDate.format(DateTimeFormatter.ofPattern("W")));
                System.out.println("Неделя года: " + localDate.format(DateTimeFormatter.ofPattern("w")));
                System.out.println("Месяц: " + localDate.getMonthValue());
                System.out.println("Год: " + localDate.getYear());
            } catch (final DateTimeParseException ignored) {
            }

            try {
                final LocalTime localTime = LocalTime.parse(time, DateTimeFormatter.ofPattern(format));

                System.out.println("AM или PM: " + localTime.format(DateTimeFormatter.ofPattern("a")));
                System.out.println("Часы: " + localTime.format(DateTimeFormatter.ofPattern("h")));
                System.out.println("Часы дня: " + localTime.getHour());
                System.out.println("Минуты: " + localTime.getMinute());
                System.out.println("Секунды: " + localTime.getSecond());
            } catch (final DateTimeParseException ignored) {
            }
        }
    }
}
