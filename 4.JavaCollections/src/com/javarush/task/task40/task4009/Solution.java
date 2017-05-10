package com.javarush.task.task40.task4009;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

/* 
Buon Compleanno!
*/

public class Solution {
    public static void main(final String[] args) {
        System.out.println(weekDayOfBirthday("30.05.1984", "2015"));
        System.out.println(weekDayOfBirthday("1.12.2015", "2016"));
    }

    public static String weekDayOfBirthday(final String birthday, final String year) {
        final Year eventYear = Year.parse(year);
        final LocalDate birthDate = LocalDate
                .parse(birthday, DateTimeFormatter.ofPattern("d.M.u"))
                .withYear(eventYear.getValue());

        return birthDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ITALY);
    }
}
