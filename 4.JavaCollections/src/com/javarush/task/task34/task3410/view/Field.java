package com.javarush.task.task34.task3410.view;

import com.javarush.task.task34.task3410.model.Box;
import com.javarush.task.task34.task3410.model.Player;

import javax.swing.*;
import java.awt.*;

public class Field extends JPanel {
    private View view;

    public Field(final View view) {
        this.view = view;
    }

    public void paint(final Graphics g) {
        final Box box = new Box(20, 20);
        box.draw(g);
        final Player player = new Player(50, 20);
        player.draw(g);
    }
}
