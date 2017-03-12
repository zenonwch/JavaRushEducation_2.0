package com.javarush.task.task35.task3513;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Model {
    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles;

    int score;
    int maxTile;

    public Model() {
        resetGameTiles();
    }

    public void resetGameTiles() {
        score = 0;
        maxTile = 2;
        gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                gameTiles[i][j] = new Tile();
            }
        }
        addTile();
        addTile();
    }

    private void addTile() {
        final List<Tile> emptyTilesList = getEmptyTiles();
        final int emptyTilesListSize = emptyTilesList.size();
        final int randomTileNumber = (int) (Math.random() * emptyTilesListSize);
        final Tile randomTile = emptyTilesList.get(randomTileNumber);
        randomTile.value = Math.random() < 0.9 ? 2 : 4;
    }

    private List<Tile> getEmptyTiles() {
        final List<Tile> emptyTiles = new ArrayList<>();
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if (gameTiles[i][j].isEmpty())
                    emptyTiles.add(gameTiles[i][j]);
            }
        }
        return emptyTiles;
    }

    private void compressTiles(final Tile[] tiles) {
        final List<Tile> tilesList = new ArrayList<>(Arrays.asList(tiles));
        final int len = tiles.length;
        for (int i = 0; i < len; i++) {
            if (tilesList.get(i).isEmpty())
                tilesList.add(tilesList.remove(i));
        }
        for (int i = 0; i < len; i++) {
            tiles[i] = tilesList.get(i);
        }
    }

    private void mergeTiles(final Tile[] tiles) {
        if (tiles[0].isEmpty())
            return;

        final List<Tile> tilesList = new ArrayList<>(Arrays.asList(tiles));
        final int len = tiles.length;

        for (int i = 0; i < len; i++) {
            final Tile current = tilesList.get(i);
            final Tile next = tilesList.get(i + 1);

            if (current.value == next.value) {
                current.value *= 2;
                next.value = 0;
                tilesList.add(tilesList.remove(i + 1));

                score += current.value;
                if (current.value > maxTile)
                    maxTile = current.value;
            }
        }

        for (int i = 0; i < len; i++) {
            tiles[i] = tilesList.get(i);
        }
    }
}
