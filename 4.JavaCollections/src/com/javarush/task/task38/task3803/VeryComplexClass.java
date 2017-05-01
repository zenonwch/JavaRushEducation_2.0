package com.javarush.task.task38.task3803;

/* 
Runtime исключения (unchecked exception)
*/

public class VeryComplexClass {
    public void methodThrowsClassCastException() {
        final Integer i = 10;
        final Object o = i;
        System.out.println((String) o);
    }

    public void methodThrowsNullPointerException() {
        final String s = null;
        System.out.println(s.length());
    }

    public static void main(final String[] args) {
    }
}
