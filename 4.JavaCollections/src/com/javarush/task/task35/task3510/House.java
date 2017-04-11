package com.javarush.task.task35.task3510;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* 
Вход воспрещен!
*/
public class House<T> {

    private List<T> residents = new ArrayList<>();

    public void enter(final T resident) {
        residents.add(resident);
    }

    public void leave(final T resident) {
        residents.remove(resident);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("В доме находятся:\n");
        for (final T resident : residents) {
            builder.append(resident.toString()).append("\n");
        }
        return builder.toString();
    }

    public static void main(final String[] args) {
        final Dog bruno = new Dog("Bruno");
        final Puppy larsik = new Puppy("Larsik");
        final Cat barsik = new Cat("Barsik");
        final Kitten keksik = new Kitten("Keksik");

        final House<Dog> dogHouse = new House<>();
        dogHouse.enter(bruno);
        dogHouse.enter(larsik);
//        dogHouse.enter(barsik);
        System.out.println(dogHouse.toString());

        final House<Cat> catHouse = new House<>();
        catHouse.enter(barsik);
        catHouse.enter(keksik);
//        catHouse.enter(bruno);
        System.out.println(catHouse.toString());
    }
}
