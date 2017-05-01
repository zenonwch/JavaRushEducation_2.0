package com.javarush.task.task38.task3802;

/* 
Проверяемые исключения (checked exception)
*/

import java.text.SimpleDateFormat;

public class VeryComplexClass {
    public void veryComplexMethod() throws Exception {
        final String string = "12abc";
        System.out.println(new SimpleDateFormat("dd.MM.yyyy").parse(string));
    }

    public static void main(final String[] args) throws Exception {
        VeryComplexClass vcc = new VeryComplexClass();
        vcc.veryComplexMethod();
    }
}
