package com.javarush.task.task34.task3410.model;

import com.javarush.task.task34.task3410.controller.EventListener;

import java.nio.file.Paths;
import java.util.Set;

public class Model {
    private EventListener eventListener;
    private GameObjects gameObjects;
    private int currentLevel = 1;
    private LevelLoader levelLoader = new LevelLoader(Paths.get("../res/levels.txt"));

    public static int FIELD_CELL_SIZE = 20;

    public void setEventListener(final EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects() {
        return gameObjects;
    }

    public void restartLevel(final int level) {
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart() {
        restartLevel(currentLevel);
    }

    public void startNextLevel() {
        currentLevel++;
        restart();
    }

    public void move(final Direction direction) {
        final Player player = gameObjects.getPlayer();
        if (checkWallCollision(player, direction) || checkBoxCollisionAndMoveIfAvaliable(direction))
            return;

        switch (direction) {
            case LEFT:
                player.move(-FIELD_CELL_SIZE, 0);
                break;
            case RIGHT:
                player.move(FIELD_CELL_SIZE, 0);
                break;
            case UP:
                player.move(0, -FIELD_CELL_SIZE);
                break;
            case DOWN:
                player.move(0, FIELD_CELL_SIZE);
                break;
        }

        checkCompletion();
    }

    public boolean checkWallCollision(final CollisionObject gameObject,
                                      final Direction direction) {
        return gameObjects.getWalls().stream()
                .anyMatch(wall -> gameObject.isCollision(wall, direction));
    }

    public boolean checkBoxCollisionAndMoveIfAvaliable(final Direction direction) {
        final Set<Box> boxes = gameObjects.getBoxes();
        for (final Box box : boxes) {
            if (gameObjects.getPlayer().isCollision(box, direction)) {
                for (final Box anotherBox : boxes) {
                    if (box.isCollision(anotherBox, direction) || checkWallCollision(box, direction))
                        return true;
                }

                switch (direction) {
                    case LEFT:
                        box.move(-FIELD_CELL_SIZE, 0);
                        break;
                    case RIGHT:
                        box.move(FIELD_CELL_SIZE, 0);
                        break;
                    case UP:
                        box.move(0, -FIELD_CELL_SIZE);
                        break;
                    case DOWN:
                        box.move(0, FIELD_CELL_SIZE);
                        break;
                }
            }
        }

        return false;
    }

    public void checkCompletion() {
        final long counter = gameObjects.getBoxes().stream()
                .mapToLong(box -> gameObjects.getHomes().stream()
                        .filter(home -> box.getX() == home.getX() && box.getY() == home.getY())
                        .count())
                .sum();

        if (counter == gameObjects.getBoxes().size()) {
            eventListener.levelCompleted(currentLevel);
        }
    }
}
