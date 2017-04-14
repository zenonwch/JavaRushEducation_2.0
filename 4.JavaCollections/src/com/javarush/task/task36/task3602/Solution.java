package com.javarush.task.task36.task3602;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.*;

/* 
Найти класс по описанию
*/
public class Solution {
    public static void main(final String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class<?> getExpectedClass() {

        for (final Class<?> cls : Collections.class.getDeclaredClasses()) {

            if (List.class.isAssignableFrom(cls)
                    && Modifier.isPrivate(cls.getModifiers())
                    && Modifier.isStatic(cls.getModifiers())) {
                try {
                    final Constructor<?> constructor = cls.getDeclaredConstructor();
                    constructor.setAccessible(true);
                    final List<?> list = (List<?>) constructor.newInstance();
                    list.get(0);
                } catch (final IndexOutOfBoundsException e) {
                    return cls;
                } catch (final Exception ignored) {
                }
            }
        }

        return null;
    }
}
