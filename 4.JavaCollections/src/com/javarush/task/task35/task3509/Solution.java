package com.javarush.task.task35.task3509;

import java.util.*;

/* 
Collections & Generics
*/
public class Solution {

    public static void main(final String[] args) {
    }

    public static <T> ArrayList<T> newArrayList(final T... elements) {
        return new ArrayList<>(Arrays.asList(elements));
    }

    public static <T> HashSet<T> newHashSet(final T... elements) {
        return new HashSet<>(Arrays.asList(elements));
    }

    public static <K, V> HashMap<K, V> newHashMap(final List <? extends K> keys, final List<? extends V> values) {
        if (keys.size() != values.size()) throw new IllegalArgumentException();

        final HashMap<K, V> map = new HashMap<>();
        for (int i = 0; i < keys.size(); i++) {
            map.put(keys.get(i), values.get(i));
        }
        return map;
    }
}
