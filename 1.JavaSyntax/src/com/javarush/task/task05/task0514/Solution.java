package com.javarush.task.task05.task0514;

/* 
Программист создает человека
*/

public class Solution {
    public static void main(final String[] args) {
        final Person person = new Person();
        person.initialize("User", 17);
    }

    static class Person {
        private String name;
        private int age;

        public void initialize(final String name, final int age) {
            this.name = name;
            this.age = age;
        }
    }
}
