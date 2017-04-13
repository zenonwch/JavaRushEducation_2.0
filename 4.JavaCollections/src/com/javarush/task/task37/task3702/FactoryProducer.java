package com.javarush.task.task37.task3702;

import com.javarush.task.task37.task3702.female.FemaleFactory;
import com.javarush.task.task37.task3702.male.MaleFactory;

public class FactoryProducer {
    public static AbstractFactory getFactory(final HumanFactoryType factoryType) {
        if (factoryType == HumanFactoryType.MALE) return new MaleFactory();
        if (factoryType == HumanFactoryType.FEMALE) return new FemaleFactory();
        return null;
    }

    public static enum HumanFactoryType {
        MALE,
        FEMALE,
    }
}
