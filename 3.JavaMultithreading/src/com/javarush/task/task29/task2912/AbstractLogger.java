package com.javarush.task.task29.task2912;

public abstract class AbstractLogger implements Logger {
    private int level;
    private Logger next;


    public AbstractLogger(final int level) {
        this.level = level;
    }

    @Override
    public void inform(final String message, final int level) {
        if (this.level <= level) {
            info(message);
        }
        if (next != null) {
            next.inform(message, level);
        }

    }

    @Override
    public void setNext(final Logger next) {
        this.next = next;
    }
}
