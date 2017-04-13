package com.javarush.task.task37.task3702.male;

import com.javarush.task.task37.task3702.Human;

public class MaleFactory {
    public Human getPerson(final int age) {
        if (age <= 12) return new KidBoy();
        return age <= 19 ? new TeenBoy() : new Man();
    }
}
