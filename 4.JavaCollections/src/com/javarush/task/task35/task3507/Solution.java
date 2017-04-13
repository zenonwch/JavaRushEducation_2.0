package com.javarush.task.task35.task3507;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

/* 
ClassLoader - что это такое?
*/
public class Solution {
    public static void main(final String[] args) {
        final Set<? extends Animal> allAnimals = getAllAnimals(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {
        final Set<Animal> animalSet = new HashSet<>();

        if (!pathToAnimals.endsWith("\\") && !pathToAnimals.endsWith("/")) pathToAnimals += "/";

        final ClassLoader classLoader = new ClassLoader() {
            @Override
            protected Class<?> findClass(final String name) throws ClassNotFoundException {
                try {
                    final byte[] bytes = Files.readAllBytes(Paths.get(name));
                    return defineClass(null, bytes, 0, bytes.length);
                } catch (final IOException e) {
                    e.printStackTrace();
                }
                return super.findClass(name);
            }
        };

        final File data = new File(pathToAnimals);
        final File[] dataFiles = data.listFiles((dir, name) -> name.endsWith(".class"));

        for (final File file : dataFiles) {
            try {
                final String filePath = file.getAbsolutePath();
                final Class clazz = classLoader.loadClass(filePath);
                final Class[] interfaces = clazz.getInterfaces();
                final Constructor[] constructors = clazz.getConstructors();

                for (final Class classInterface : interfaces) {
                    if (classInterface.equals(Animal.class)) {
                        for (final Constructor constructor : constructors) {
                            if (Modifier.isPublic(constructor.getModifiers()) && constructor.getParameterCount() == 0)
                                animalSet.add((Animal) clazz.newInstance());
                        }
                    }
                }
            } catch (final ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return animalSet;
    }
}