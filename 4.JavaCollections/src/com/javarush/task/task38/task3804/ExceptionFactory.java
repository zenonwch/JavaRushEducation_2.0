package com.javarush.task.task38.task3804;

public class ExceptionFactory {
    public static Throwable getException(final Enum exception) {
        if (exception != null) {
            final String message = exception.name().charAt(0) + exception.name().substring(1).toLowerCase().replaceAll("_", " ");
            if (exception instanceof ExceptionApplicationMessage)
                return new Exception(message);
            if (exception instanceof ExceptionDBMessage)
                return new RuntimeException(message);
            if (exception instanceof ExceptionUserMessage)
                return new Error(message);
        }
        return new IllegalArgumentException();
    }
}
