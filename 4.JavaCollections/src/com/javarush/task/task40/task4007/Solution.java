package com.javarush.task.task40.task4007;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/* 
Работа с датами
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
        final Calendar calendar = Calendar.getInstance();

        final String[] formatStrings = {"dd.MM.yyyy", "hh:mm:ss"};

        for (final String formatString : formatStrings) {
            final SimpleDateFormat format = new SimpleDateFormat(formatString);
            for (final String part : date.split(" ")) {
                try {
                    calendar.setTime(format.parse(part));
                } catch (final ParseException ignored) {
                    continue;
                }

                final int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

                if (formatString.contains(".")) {
                    System.out.println("День: " + calendar.get(Calendar.DATE));
                    System.out.println("День недели: " + (dayOfWeek == 1 ? 7 : dayOfWeek - 1));
                    System.out.println("День месяца: " + calendar.get(Calendar.DAY_OF_MONTH));
                    System.out.println("День года: " + calendar.get(Calendar.DAY_OF_YEAR));
                    System.out.println("Неделя месяца: " + calendar.get(Calendar.WEEK_OF_MONTH));
                    System.out.println("Неделя года: " + calendar.get(Calendar.WEEK_OF_YEAR));
                    System.out.println("Месяц: " + (calendar.get(Calendar.MONTH) + 1));
                    System.out.println("Год: " + calendar.get(Calendar.YEAR));
                }

                if (formatString.contains(":")) {
                    System.out.println("AM или PM: " + (calendar.get(Calendar.AM_PM) == 0 ? "AM" : "PM"));
                    System.out.println("Часы: " + calendar.get(Calendar.HOUR));
                    System.out.println("Часы дня: " + calendar.get(Calendar.HOUR_OF_DAY));
                    System.out.println("Минуты: " + calendar.get(Calendar.MINUTE));
                    System.out.println("Секунды: " + calendar.get(Calendar.SECOND));
                }
            }
        }
    }
}
