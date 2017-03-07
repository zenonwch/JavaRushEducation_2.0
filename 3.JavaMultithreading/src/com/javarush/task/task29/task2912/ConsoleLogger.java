package com.javarush.task.task29.task2912;

public class ConsoleLogger extends AbstractLogger {

    public ConsoleLogger(final int level) {
        super(level);
    }

    @Override
    public void info(final String message) {
        System.out.println("Logging to console: " + message);
    }
}