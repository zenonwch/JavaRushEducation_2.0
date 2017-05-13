package com.javarush.task.task34.task3410.controller;

import com.javarush.task.task34.task3410.model.Direction;
import com.javarush.task.task34.task3410.model.GameObjects;
import com.javarush.task.task34.task3410.model.Model;
import com.javarush.task.task34.task3410.view.View;

public class Controller implements EventListener {
    private View view;
    private Model model;

    public Controller() {
        view = new View(this);
        model = new Model();
        model.restart();
        model.setEventListener(this);
        view.init();
        view.setEventListener(this);
    }

    @Override
    public void move(final Direction direction) {
        model.move(direction);
        view.update();
    }

    @Override
    public void restart() {
        model.restart();
        view.update();
    }

    @Override
    public void startNextLevel() {
        model.startNextLevel();
        view.update();
    }

    @Override
    public void levelCompleted(final int level) {
        view.completed(level);
    }

    public GameObjects getGameObjects() {
        return model.getGameObjects();
    }

    public static void main(final String[] args) {
        final Controller controller = new Controller();
    }
}
