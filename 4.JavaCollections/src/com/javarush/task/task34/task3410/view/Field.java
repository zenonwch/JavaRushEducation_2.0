package com.javarush.task.task34.task3410.view;

import com.javarush.task.task34.task3410.controller.EventListener;
import com.javarush.task.task34.task3410.model.Box;
import com.javarush.task.task34.task3410.model.Home;
import com.javarush.task.task34.task3410.model.Player;
import com.javarush.task.task34.task3410.model.Wall;

import javax.swing.*;
import java.awt.*;

public class Field extends JPanel {
    private View view;
    private EventListener eventListener;

    public Field(final View view) {
        this.view = view;
    }

    public void setEventListener(final EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public void paint(final Graphics g) {
        final Box box = new Box(20, 20);
        box.draw(g);
        final Player player = new Player(50, 20);
        player.draw(g);
        final Home home = new Home(20, 50);
        home.draw(g);
        final Wall wall = new Wall(50, 50);
        wall.draw(g);
    }
}
