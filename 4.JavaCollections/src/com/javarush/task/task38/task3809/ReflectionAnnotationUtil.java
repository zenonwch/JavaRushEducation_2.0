package com.javarush.task.task38.task3809;

import java.lang.reflect.Field;

public final class ReflectionAnnotationUtil {
    public static void check(final Object someObject) throws IllegalAccessException {
        final Class<?> testedClass = someObject.getClass();
        for (final Field field : testedClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(LongPositive.class)) {
                processLongPositiveAnnotationField(someObject, testedClass, field);
            }
        }
    }

    private static void processLongPositiveAnnotationField(final Object someObject, final Class<?> testedClass, final Field field) throws
            IllegalAccessException
    {
        field.setAccessible(true);
        final Class<?> fieldType = field.getType();

        //assert type is long
        if (!fieldType.equals(long.class)) {
            final String msg = String.format("Поле %s в классе %s имеет аннотацию LongPositive, но его тип %s.",
                    field.getName(), testedClass.getSimpleName(), fieldType.getSimpleName());
            System.out.println(msg);
            return;
        }

        //assert value is positive
        final long value = (long) field.get(someObject);
        if (value <= 0) {
            final String msg = String.format("Поле %s в классе %s имеет аннотацию LongPositive, но его значение не положительное.",
                    field.getName(), testedClass.getSimpleName());
            System.out.println(msg);
        }
    }
}
