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
        view.init();
    }

    @Override
    public void move(final Direction direction) {

    }

    @Override
    public void restart() {

    }

    @Override
    public void startNextLevel() {

    }

    @Override
    public void levelCompleted(final int level) {

    }

    public GameObjects getGameObjects() {
        return model.getGameObjects();
    }

    public static void main(final String[] args) {
        final Controller controller = new Controller();
    }
}
