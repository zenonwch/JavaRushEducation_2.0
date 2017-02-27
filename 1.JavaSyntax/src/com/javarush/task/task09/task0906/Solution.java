package com.javarush.task.task09.task0906;

/* 
Логирование стек трейса
*/

public class Solution {
    public static void main(final String[] args) {
        log("In main method");
    }

    public static void log(final String s) {
        final StackTraceElement[] stackElements = Thread.currentThread().getStackTrace();
        final StackTraceElement element = stackElements[2];
        System.out.println(element.getClassName() + ": " + element.getMethodName() + ": " + s);
    }
}
