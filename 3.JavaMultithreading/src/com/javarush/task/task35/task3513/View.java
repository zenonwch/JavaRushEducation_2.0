package com.javarush.task.task35.task3513;

import javax.swing.*;
import java.awt.*;

public class View extends JPanel {
    private static final Color BG_COLOR = new Color(0xbbada0);
    private static final String FONT_NAME = "Arial";
    private static final int TILE_SIZE = 96;
    private static final int TILE_MARGIN = 12;

    private Controller controller;

    boolean isGameWon;
    boolean isGameLost;

    public View(final Controller controller) {
        setFocusable(true);
        this.controller = controller;
        addKeyListener(controller);
    }

    @Override
    public void paint(final Graphics g) {
        super.paint(g);
        g.setColor(BG_COLOR);
        g.fillRect(0, 0, getSize().width, getSize().height);
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                drawTile(g, controller.getGameTiles()[y][x], x, y);
            }
        }

        g.drawString("Score: " + controller.getScore(), 140, 465);
    }

    public void theEnd() {
        if (isGameWon) {
            JOptionPane.showMessageDialog(this, "You've won!");
            controller.resetGame();
        } else if(isGameLost) {
            JOptionPane.showMessageDialog(this, "You've lost :(");
            controller.resetGame();
        }
    }

    private void drawTile(final Graphics g2, final Tile tile, final int x, final int y) {
        final Graphics2D g = (Graphics2D) g2;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        final int value = tile.value;
        final int xOffset = offsetCoors(x);
        final int yOffset = offsetCoors(y);
        g.setColor(tile.getTileColor());
        g.fillRoundRect(xOffset, yOffset, TILE_SIZE, TILE_SIZE , 8, 8);
        g.setColor(tile.getFontColor());
        final int size = value < 100 ? 36 : value < 1000 ? 32 : 24;
        final Font font = new Font(FONT_NAME, Font.BOLD, size);
        g.setFont(font);

        final String s = String.valueOf(value);
        final FontMetrics fm = getFontMetrics(font);

        final int w = fm.stringWidth(s);
        final int h = -(int) fm.getLineMetrics(s, g).getBaselineOffsets()[2];

        if (value != 0)
            g.drawString(s, xOffset + (TILE_SIZE - w) / 2, yOffset + TILE_SIZE - (TILE_SIZE - h) / 2 - 2);
    }

    private static int offsetCoors(final int arg) {
        return arg * (TILE_MARGIN + TILE_SIZE) + TILE_MARGIN;
    }
}
