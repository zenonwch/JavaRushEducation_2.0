package com.javarush.task.task34.task3410.model;

import com.javarush.task.task34.task3410.controller.EventListener;

import java.nio.file.Paths;

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
}
