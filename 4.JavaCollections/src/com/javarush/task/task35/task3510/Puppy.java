package com.javarush.task.task35.task3510;

public class Puppy extends Dog {

    public Puppy(final String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Щенок " + name;
    }
}
