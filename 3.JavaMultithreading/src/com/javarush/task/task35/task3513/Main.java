package com.javarush.task.task35.task3513;

import javax.swing.*;

public class Main {
    public static void main(final String[] args) {
        final Model model = new Model();
        final Controller controller = new Controller(model);
        final JFrame gameView = new JFrame();

        gameView.setTitle("2048");
        gameView.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameView.setSize(450, 500);
        gameView.setResizable(false);

        gameView.add(controller.getView());

        gameView.setLocationRelativeTo(null);
        gameView.setVisible(true);
    }
}