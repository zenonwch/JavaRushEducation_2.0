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
    }
}