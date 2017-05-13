package com.javarush.task.task34.task3410.model;

import java.awt.*;

public class Wall extends CollisionObject {
    public Wall(final int x, final int y) {
        super(x, y);
    }

    @Override
    public void draw(final Graphics graphics) {
        final int newX = getX() - getWidth() / 2;
        final int newY = getY() - getHeight() / 2;

        graphics.setColor(Color.GRAY);
        graphics.fillRect(newX, newY, getWidth(), getHeight());
    }
}
