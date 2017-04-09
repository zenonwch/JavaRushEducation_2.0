package com.javarush.task.task35.task3501;
/* 
Вызов статического метода
*/
public class Solution {
    public static void main(final String[] args) {
        final Number number = GenericStatic.someStaticMethod(new Integer(3));
    }
}
