package com.javarush.task.task29.task2912;

public interface Logger {
    void inform(final String message, final int level);

    void setNext(final Logger next);

    void info(final String message);
}