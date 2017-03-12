package com.javarush.task.task30.task3012;

/* 
Получи заданное число
*/

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.createExpression(74);
        solution.createExpression(1234);
        solution.createExpression(2);
    }

    public void createExpression(int number) {
        if (number < 1 || number > 3000) return;
        final StringBuilder sb = new StringBuilder(Integer.toString(number, 3));
        final String[] arr = sb.reverse().toString().split("");
        final StringBuilder res = new StringBuilder(Integer.toString(number) + " = ");
        int next = 0;
        for (int i = 0; i < arr.length; i++) {
            int n = Integer.parseInt(arr[i]);
            n += next;
            switch (n) {
                case 3:
                    next = 1;
                    break;
                case 2:
                    res.append("- ").append((int) StrictMath.pow(3, i)).append(' ');
                    next = 1;
                    break;
                case 1:
                    res.append("+ ").append((int) StrictMath.pow(3, i)).append(' ');
                    next = 0;
                    break;
                default:
                    next = 0;
                    break;
            }

            if (i == arr.length - 1 && next == 1) {
                res.append("+ ").append((int) StrictMath.pow(3, i + 1));
            }
        }
        System.out.println(res);
    }
}