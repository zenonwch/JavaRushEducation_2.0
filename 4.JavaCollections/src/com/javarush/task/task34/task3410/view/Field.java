package com.javarush.task.task34.task3410.view;

import com.javarush.task.task34.task3410.controller.EventListener;
import com.javarush.task.task34.task3410.model.GameObject;
import com.javarush.task.task34.task3410.model.GameObjects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static com.javarush.task.task34.task3410.model.Direction.*;
import static java.awt.event.KeyEvent.*;

public class Field extends JPanel {
    private View view;
    private EventListener eventListener;

    public Field(final View view) {
        this.view = view;
        final KeyHandler keyHandler = new KeyHandler();
        addKeyListener(keyHandler);
        setFocusable(true);
    }

    public void setEventListener(final EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public void paint(final Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getSize().width, getSize().height);

        final GameObjects gameObjects = view.getGameObjects();
        for (final GameObject gameObject : gameObjects.getAll()) {
            gameObject.draw(g);
        }
    }

    public class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(final KeyEvent e) {
            switch (e.getKeyCode()) {
                case VK_LEFT:
                    eventListener.move(LEFT);
                    break;
                case VK_RIGHT:
                    eventListener.move(RIGHT);
                    break;
                case VK_UP:
                    eventListener.move(UP);
                    break;
                case VK_DOWN:
                    eventListener.move(DOWN);
                    break;
                case VK_R:
                    eventListener.restart();
                    break;
            }
        }
    }
}
