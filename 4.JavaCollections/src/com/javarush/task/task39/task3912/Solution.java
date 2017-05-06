package com.javarush.task.task39.task3912;

/* 
Максимальная площадь
*/

public class Solution {
    public static void main(final String[] args) {
        final int[][] matrix = {
                {1, 0, 1, 0, 1},
                {1, 1, 1, 0, 1},
                {1, 0, 1, 1, 1},
                {1, 0, 1, 1, 1},
                {1, 0, 1, 1, 1},
                {0, 1, 1, 1, 1}
        };

        System.out.println(maxSquare(matrix));
    }

    public static int maxSquare(final int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;

        final int h = matrix.length;
        final int w = matrix[0].length;

        final int[][] matrixCopy = new int[h][w];
        for (int i = 0; i < h; i++) {
            System.arraycopy(matrix[i], 0, matrixCopy[i], 0, w);
        }

        int maxSide = 0;

        for (int y = 1; y < h; y++) {
            for (int x = 1; x < w; x++) {
                if (matrixCopy[y][x] == 1) {
                    matrixCopy[y][x] = Math.min(matrixCopy[y][x - 1], Math.min(matrixCopy[y - 1][x], matrixCopy[y - 1][x - 1])) + 1;
                    maxSide = matrixCopy[y][x] > maxSide ? matrixCopy[y][x] : maxSide;
                }
            }
        }

        return maxSide * maxSide;
    }
}
