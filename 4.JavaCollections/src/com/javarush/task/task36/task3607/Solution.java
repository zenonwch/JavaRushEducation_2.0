package com.javarush.task.task36.task3607;


import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/*
Найти класс по описанию
*/
public class Solution {
    public static void main(final String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        final Set<String> classNames = new HashSet<>();

        //to avoid this mess you can start search within java.util package
        final URLClassLoader classLoader = (URLClassLoader) Thread.currentThread().getContextClassLoader();
        final URL[] jarsPath = classLoader.getURLs();

        for (final URL jarPath : jarsPath) {
            if (jarPath.toString().endsWith("rt.jar")) {
                try {
                    final JarFile jarFile = new JarFile(jarPath.getPath().toString());
                    final Enumeration<JarEntry> en = jarFile.entries();
                    while (en.hasMoreElements()) {
                        final String fileName = en.nextElement().getName();
                        if (fileName.startsWith("java/util") && fileName.endsWith(".class")) {
                            classNames.add(fileName);
                        }
                    }
                } catch (final IOException ignored) {
                }
            }
        }

        for (final String className : classNames) {
            try {
                final String fileName = className.substring(0, className.length() - 6).replace('/', '.');
                final Class cls = Class.forName(fileName);
                if (Queue.class.isAssignableFrom(cls) && cls.getEnclosingClass() == null) {
                    final Field[] fields = cls.getDeclaredFields();
                    final Method[] methods = cls.getDeclaredMethods();
                    if (Arrays.stream(methods)
                            .filter(m -> m.getName().contains("peekExpired"))
                            .flatMap(m -> Arrays.stream(fields))
                            .anyMatch(f -> f.getType().equals(Thread.class))) {
                        return cls;
                    }
                }
            } catch (final ClassNotFoundException ignored) {
            }
        }
        return null;
    }
}
