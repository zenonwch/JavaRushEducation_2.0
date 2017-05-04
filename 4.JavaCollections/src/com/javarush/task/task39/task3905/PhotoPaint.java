package com.javarush.task.task39.task3905;

public class PhotoPaint {
    public boolean paintFill(final Color[][] image, final int r, final int c, final Color desiredColor) {
        if (r < 0 || c < 0 || r >= image.length || c >= image[r].length || image[c][r] == desiredColor) {
            return false;
        }

        image[c][r] = desiredColor;

        return true;
    }
}