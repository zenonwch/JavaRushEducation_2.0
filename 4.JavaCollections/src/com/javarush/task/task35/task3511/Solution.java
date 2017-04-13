package com.javarush.task.task35.task3511;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* 
Wildcards для коллекций
*/
public class Solution {

    public static void main(final String[] args) {
    }

    public static Double sum(final List<? extends Number> list) {
        Double result = 0.0;
        for (int i = 0; i < list.size(); i++) {
            final Number numb = list.get(i);
            result += numb.doubleValue();
        }
        return result;
    }

    public static Double multiply(final List<? extends Number> list) {
        Double result = 1.0;
        for (int i = 0; i < list.size(); i++) {
            final Number numb = list.get(i);
            result *= numb.doubleValue();
        }
        return result;
    }

    public static String concat(final List<?> list) {
        final StringBuilder builder = new StringBuilder();
        for (final Object obj : list) {
            builder.append(obj);
        }
        return builder.toString();
    }

    public static List<Object> combine(final List<? extends Collection> list) {
        final List<Object> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            final Collection collection = list.get(i);
            result.addAll(collection);
        }
        return result;
    }
}
