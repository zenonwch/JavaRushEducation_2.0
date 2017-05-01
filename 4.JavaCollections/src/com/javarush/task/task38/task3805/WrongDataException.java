package com.javarush.task.task38.task3805;

public class WrongDataException extends Exception {
    public WrongDataException() {
        super();
    }

    public WrongDataException(final String message) {
        super(message);
    }
}
