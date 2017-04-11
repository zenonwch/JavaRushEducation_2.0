package com.javarush.task.task35.task3505;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvertableUtil {

    public static <K, T extends Convertable<K>> Map<K, T> convert(final List<T> list) {
        final Map<K, T> result = new HashMap<>();
        for (final T key : list) {
            result.put(key.getKey(), key);
        }
        return result;
    }
}
