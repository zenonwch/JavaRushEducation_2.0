package com.javarush.task.task08.task0818;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* 
Только для богачей
*/

public class Solution {
    public static HashMap<String, Integer> createMap() {
        final HashMap<String, Integer> map = new HashMap<>();
        map.put("a", 270);
        map.put("b", 1270);
        map.put("c", 720);
        map.put("d", 499);
        map.put("e", 500);
        map.put("f", 530);
        map.put("g", 501);
        map.put("h", 5000);
        map.put("i", 3270);
        map.put("j", 10);
        return map;
    }

    public static void removeItemFromMap(final HashMap<String, Integer> map) {
//      /** Java 8 syntax doesn't support */
//        map.entrySet().removeIf(s -> {
//            final int value = s.getValue();
//            return value < 500;
//        });
        for (final Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<String, Integer> entry = it.next();
            if (entry.getValue() < 500) it.remove();
        }
    }

    public static void main(final String[] args) {
    }
}