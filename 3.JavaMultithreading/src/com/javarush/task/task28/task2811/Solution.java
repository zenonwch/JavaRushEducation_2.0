package com.javarush.task.task28.task2811;

/* 
ReentrantReadWriteLock
*/

import java.util.LinkedHashMap;

public class Solution {
    public static void main(final String[] args) {
        final ReadWriteMap<Integer, String> linkedSafeMap = new ReadWriteMap<>(new LinkedHashMap<>());
    }
}
