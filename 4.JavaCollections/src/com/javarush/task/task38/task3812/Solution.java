package com.javarush.task.task38.task3812;

/* 
Обработка аннотаций
*/

public class Solution {
    public static void main(final String[] args) {
        printFullyQualifiedNames(Solution.class);
        printFullyQualifiedNames(SomeTest.class);

        printValues(Solution.class);
        printValues(SomeTest.class);
    }

    public static boolean printFullyQualifiedNames(final Class c) {
        if (c.isAnnotationPresent(PrepareMyTest.class)) {
            final PrepareMyTest annotation = (PrepareMyTest) c.getAnnotation(PrepareMyTest.class);
            for (final String name : annotation.fullyQualifiedNames()) {
                System.out.println(name);
            }
            return true;
        }
        return false;
    }

    public static boolean printValues(final Class c) {
        if (c.isAnnotationPresent(PrepareMyTest.class)) {
            final PrepareMyTest annotation = (PrepareMyTest) c.getAnnotation(PrepareMyTest.class);
            for (final Class cls : annotation.value()) {
                System.out.println(cls.getSimpleName());
            }
            return true;
        }
        return false;
    }
}
