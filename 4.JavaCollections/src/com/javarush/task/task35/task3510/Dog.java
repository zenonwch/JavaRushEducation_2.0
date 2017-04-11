package com.javarush.task.task35.task3510;

public class Dog {

    protected String name;

    public Dog(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Пес " + name;
    }
}
