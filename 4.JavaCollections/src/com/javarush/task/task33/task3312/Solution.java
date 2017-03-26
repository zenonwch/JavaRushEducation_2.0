package com.javarush.task.task33.task3312;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/* 
Сериализация зоопарка
*/
public class Solution {
    public static void main(final String[] args) throws JsonProcessingException {
        final Zoo.Dog dog = new Zoo.Dog("doggy");
        final Zoo.Cat cat = new Zoo.Cat("catty");
        final Zoo zoo = new Zoo();
        zoo.animals.add(dog);
        zoo.animals.add(cat);

        final String result = new ObjectMapper().writeValueAsString(zoo);

        System.out.println(result);
    }
}
