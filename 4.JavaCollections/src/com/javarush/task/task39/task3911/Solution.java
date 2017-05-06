package com.javarush.task.task39.task3911;

import java.util.Map;

/* 
Rollback
*/

public class Solution {
    public static void main(final String[] args) {
        final Software software = new Software();

        final int n = 3;
        for (int i = 1; i < 7; i++) {
            software.addNewVersion(i, "description of version #" + i);
        }

        System.out.println("printing all versions ");
        for (final Map.Entry entry : software.getVersionHistoryMap().entrySet()) {
            System.out.println(entry.getKey() + " :: " + entry.getValue());
        }
        System.out.println("current version is " + software.getCurrentVersion());

        System.out.println("ROLLING BACK to version " + n);
        software.rollback(n);

        System.out.println("\nprinting all versions ");
        for (final Map.Entry entry : software.getVersionHistoryMap().entrySet()) {
            System.out.println(entry.getKey() + " :: " + entry.getValue());
        }
        System.out.println("current version is " + software.getCurrentVersion());
    }
}
