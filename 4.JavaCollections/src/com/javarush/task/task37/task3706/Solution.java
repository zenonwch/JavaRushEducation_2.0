package com.javarush.task.task37.task3706;

import java.util.Arrays;
import java.util.List;

/* 
Давно забытый Array
*/
public class Solution {
    public static void main(final String[] args) {
        final List<Number> numbers = Arrays.asList(1, 2, 3);
        addDataToList(numbers, getData());
        System.out.println(numbers);
    }

    public static Number[] getData() {
        return new Number[]{};
    }

    public static void addDataToList(final List<Number> list, final Number... data) {
        for (final Number number : data) {
            list.add(number);
        }
    }
}
