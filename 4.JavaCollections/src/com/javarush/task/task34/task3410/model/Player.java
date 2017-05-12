package com.javarush.task.task34.task3410.model;

import java.awt.*;

public class Player extends CollisionObject implements Movable {
    public Player(final int x, final int y) {
        super(x, y);
    }

    @Override
    public void move(final int x, final int y) {
        setX(getX() + x);
        setY(getY() + y);
    }

    @Override
    public void draw(final Graphics graphics) {
        final int newX = getX() - getWidth()/2;
        final int newY = getY() - getHeight()/2;

        graphics.setColor(Color.YELLOW);
        graphics.fillOval(newX, newY, getWidth(), getHeight());
    }
}
