package com.javarush.task.task36.task3606;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/* 
Осваиваем ClassLoader и Reflection
*/
public class Solution {
    private List<Class> hiddenClasses = new ArrayList<>();
    private String packageName;

    public Solution(final String packageName) {
        this.packageName = packageName;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Solution solution = new Solution(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "com/javarush/task/task36/task3606/data/second");
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplse"));
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplf"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
    }

    public void scanFileSystem() throws ClassNotFoundException {

        final ClassLoader classLoader = new ClassLoader() {
            @Override
            protected Class<?> findClass(final String name) throws ClassNotFoundException {
                try {
                    final byte[] bytes = Files.readAllBytes(Paths.get(name));
                    return defineClass(null, bytes, 0, bytes.length);
                } catch (final IOException ignored) {
                }
                return super.findClass(name);
            }
        };

        final File data = new File(packageName);
        final File[] dataFiles = data.listFiles((dir, name) -> name.endsWith(".class"));

        for (final File file : dataFiles) {
            final String filePath = file.getAbsolutePath() + File.separator;
            final Class clazz = classLoader.loadClass(filePath);

            if (HiddenClass.class.isAssignableFrom(clazz)) {
                hiddenClasses.add(clazz);
            }
        }
    }

    public HiddenClass getHiddenClassObjectByKey(final String key) {
        for (final Class cls : hiddenClasses) {
            try {
                if (cls.getSimpleName().toLowerCase().startsWith(key.toLowerCase())) {
                    final Constructor<HiddenClass> ctor = cls.getDeclaredConstructor();
                    ctor.setAccessible(true);
                    return ctor.newInstance();
                }
            } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException | InstantiationException  ignored) {
            }
        }
        return null;
    }
}

