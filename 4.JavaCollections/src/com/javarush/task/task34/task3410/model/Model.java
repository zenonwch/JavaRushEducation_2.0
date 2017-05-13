package com.javarush.task.task34.task3410.model;

import com.javarush.task.task34.task3410.controller.EventListener;

public class Model {
    private EventListener eventListener;

    public static int FIELD_CELL_SIZE = 20;

    public void setEventListener(final EventListener eventListener) {
        this.eventListener = eventListener;
    }
}
