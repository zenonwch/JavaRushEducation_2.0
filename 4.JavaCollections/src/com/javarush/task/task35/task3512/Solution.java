package com.javarush.task.task35.task3512;

import java.lang.reflect.InvocationTargetException;

/*
Генератор объектов
*/
public class Solution {

    public static void main(final String[] args) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final Generator<Event> eventGenerator = new Generator<>(Event.class);

        System.out.println(eventGenerator.newInstance().getId());
        System.out.println(eventGenerator.newInstance().getId());
        System.out.println(eventGenerator.newInstance().getId());
    }

}
