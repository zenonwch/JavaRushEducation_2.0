package com.javarush.task.task36.task3607;


import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
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
        final Set<Class> classes = new HashSet<>();
        final String packageName = Queue.class.getPackage().getName().replaceAll("\\.", "/");

        final ClassLoader cl = Thread.currentThread().getContextClassLoader();
        final URL enn = cl.getResource(packageName + "/Queue.class");
        final String jarFilePath = enn.toString().split("file:")[1].split("!/" + packageName)[0];

        try {
            final JarFile jarFile = new JarFile(jarFilePath);
            final List<? extends JarEntry> jarEntryList = Collections.list(jarFile.entries());

            for (final JarEntry jarEntry : jarEntryList) {
                if (jarEntry.isDirectory()) continue;
                final String fileName = jarEntry.getName();
                if (fileName.startsWith(packageName + "/concurrent") && fileName.endsWith(".class") && !fileName.contains("$")) {
                    final String classPath = fileName.substring(0, fileName.length() - 6).replaceAll("[\\|/]", ".");
                    classes.add(Class.forName(classPath));
                }
            }
        } catch (final IOException | ClassNotFoundException ignored) {
        }

        for (final Class cls : classes) {
            if (Queue.class.isAssignableFrom(cls) && cls.getEnclosingClass() == null && !cls.isInterface()) {
                final Method[] methods = cls.getDeclaredMethods();
                for (final Method m : methods) {
                    if (m.getName().toLowerCase().contains("expired")) {
                        return cls;
                    }
                }
            }
        }
        return null;
    }
}
