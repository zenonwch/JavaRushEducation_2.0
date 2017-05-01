package com.javarush.task.task39.task3913;

import java.nio.file.Paths;
import java.util.Date;
import java.util.Map;

public class Solution {
    public static void main(final String[] args) {
        final LogParser logParser = new LogParser(Paths.get("c:/temp/logs/"));
        System.out.println(logParser.getNumberOfUniqueIPs(null, new Date()));
        System.out.println(logParser.getDateWhenUserLoggedFirstTime("Vasya Pupkin", null, null));
        final Map<Integer, Integer> solved = logParser.getAllSolvedTasksAndTheirNumber(null, null);
        for (final Map.Entry<Integer, Integer> pair : solved.entrySet()) {
            System.out.println(pair.getKey() + ": " + pair.getValue());
        }
        System.out.println(logParser.execute("get event for date = \"30.01.2014 12:56:22\""));
        System.out.println(logParser.execute("get event for status = \"OK\""));
        for (final Object obj : logParser.execute("get status for date = \"30.01.2014 12:56:22\"")) {
            System.out.println(obj);
        }
        for (final Object obj : logParser.execute("get event")) {
            System.out.println(obj);
        }
        for (final Object obj : logParser.execute("get ip for user = \"Eduard Petrovich Morozko\" and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\".")) {
            System.out.println(obj);
        }
    }
}