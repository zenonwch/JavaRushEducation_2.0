package com.javarush.task.task06.task0605;


import java.io.*;

/* 
Контролируем массу тела
*/

public class Solution {

    public static void main(final String[] args) throws IOException {
        BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
        final double weight = Double.parseDouble(bis.readLine());
        final double height = Double.parseDouble(bis.readLine());

        Body.massIndex(weight, height);
    }

    public static class Body {
        public static void massIndex(final double weight, final double height) {
            final double index = weight / (height * height);

            if (index < 18.5) System.out.println("Недовес: меньше чем 18.5");
            else if (index < 25) System.out.println("Нормальный: между 18.5 и 24.9");
            else if (index < 30) System.out.println("Избыточный вес: между 25 и 29.9");
            else System.out.println("Ожирение: 30 или больше");
        }
    }
}
