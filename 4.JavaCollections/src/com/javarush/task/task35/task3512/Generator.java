package com.javarush.task.task35.task3512;

import java.lang.reflect.InvocationTargetException;

public class Generator<T> {

    private Class<T> clazz;

    public Generator(final Class<T> clazz) {
        this.clazz = clazz;
    }

    T newInstance() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        return clazz.getConstructor().newInstance();
    }
}
