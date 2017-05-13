package com.javarush.task.task34.task3410.model;

import java.awt.*;

public class Home extends GameObject {
    public Home(final int x, final int y) {
        super(x, y);
        setHeight(2);
        setWidth(2);
    }

    @Override
    public void draw(final Graphics graphics) {
        final int newX = getX() - getWidth() / 2;
        final int newY = getY() - getHeight() / 2;

        graphics.setColor(Color.RED);
        graphics.drawOval(newX, newY, getWidth(), getHeight());
    }
}
