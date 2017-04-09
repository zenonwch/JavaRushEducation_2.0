package com.javarush.task.task35.task3501;

public class GenericStatic {
    public static <T> T someStaticMethod(final T genericObject) {
        System.out.println(genericObject);
        return genericObject;
    }
}
