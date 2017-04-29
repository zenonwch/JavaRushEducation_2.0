package com.javarush.task.task39.task3913;

import java.nio.file.Paths;
import java.util.Date;

public class Solution {
    public static void main(final String[] args) {
        final LogParser logParser = new LogParser(Paths.get("c:/temp/logs/"));
        System.out.println(logParser.getNumberOfUniqueIPs(null, new Date()));
        System.out.println(logParser.getDateWhenUserLoggedFirstTime("Vasya Pupkin", null, null));
    }
}