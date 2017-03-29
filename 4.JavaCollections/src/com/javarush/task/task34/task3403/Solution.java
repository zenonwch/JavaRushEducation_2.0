package com.javarush.task.task34.task3403;

/* 
Разложение на множители с помощью рекурсии
*/
public class Solution {

    public void recursion(final int n) {
        final int l = (int) Math.sqrt(n);
        for (int i = 2; i <= l; i++) {
            if (n % i == 0) {
                System.out.print(i + " ");
                recursion(n / i);
                break;
            }
            if (i == l) {
                System.out.print(n);
            }
        }
    }
}
