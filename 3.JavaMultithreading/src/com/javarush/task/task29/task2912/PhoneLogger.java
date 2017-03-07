package com.javarush.task.task29.task2912;

public class PhoneLogger extends AbstractLogger {

    public PhoneLogger(final int level) {
        super(level);
    }

    @Override
    public void info(final String message) {
        System.out.println("Call to director: " + message);
    }
}