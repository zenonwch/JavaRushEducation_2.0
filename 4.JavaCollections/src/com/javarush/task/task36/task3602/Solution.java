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

            final Set<Class<?>> interfaces = new HashSet<>();
            getAllInterfaces(cls, interfaces);

            if (interfaces.contains(List.class)
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

    private static void getAllInterfaces(final Class<?> clazz, final Set<Class<?>> interfaces) {
        if (clazz == null) return;
        if (clazz.getInterfaces().length == 0) return;
        interfaces.addAll(Arrays.asList(clazz.getInterfaces()));
        for (final Class<?> intf : clazz.getInterfaces()) {
            getAllInterfaces(intf, interfaces);
            getAllInterfaces(intf.getSuperclass(), interfaces);
        }
        getAllInterfaces(clazz.getSuperclass(), interfaces);
    }
}
