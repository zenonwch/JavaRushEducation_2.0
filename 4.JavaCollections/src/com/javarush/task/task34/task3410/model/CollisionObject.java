package com.javarush.task.task34.task3410.model;

import static com.javarush.task.task34.task3410.model.Model.FIELD_CELL_SIZE;

public abstract class CollisionObject extends GameObject {
    public CollisionObject(final int x, final int y) {
        super(x, y);
    }

    public boolean isCollision(final GameObject gameObject, final Direction direction) {
        int deltaX = 0;
        int deltaY = 0;

        switch (direction) {
            case LEFT:
                deltaX = -FIELD_CELL_SIZE;
                break;
            case RIGHT:
                deltaX = FIELD_CELL_SIZE;
                break;
            case UP:
                deltaY = -FIELD_CELL_SIZE;
                break;
            case DOWN:
                deltaY = FIELD_CELL_SIZE;
                break;
        }

        return (getX() + deltaX) == gameObject.getX() && (getY() + deltaY) == gameObject.getY();
    }
}
