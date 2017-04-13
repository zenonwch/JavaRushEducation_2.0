package com.javarush.task.task37.task3702.female;

import com.javarush.task.task37.task3702.AbstractFactory;
import com.javarush.task.task37.task3702.Human;

public class FemaleFactory implements AbstractFactory {
    @Override
    public Human getPerson(final int age) {
        if (age <= 12) return new KidGirl();
        return age <= 19 ? new TeenGirl() : new Woman();
    }
}
