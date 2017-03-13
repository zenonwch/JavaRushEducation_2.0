package com.javarush.task.task35.task3513;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

    public void left() {
        boolean needToAddTile = false;

        for (int i = 0; i < FIELD_WIDTH; i++) {
            final boolean compressed = compressTiles(gameTiles[i]);
            final boolean merged = mergeTiles(gameTiles[i]);

            if (compressed || merged)
                needToAddTile = true;
        }

        if (needToAddTile) addTile();
    }

    public void right() {
        boolean needToAddTile = false;
        final Tile[] reverseRow = new Tile[FIELD_WIDTH];

        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                reverseRow[j] = gameTiles[i][FIELD_WIDTH - 1 - j];
            }
            final boolean compressed = compressTiles(reverseRow);
            final boolean merged = mergeTiles(reverseRow);

            if (compressed || merged)
                needToAddTile = true;

            for (int j = 0; j < FIELD_WIDTH; j++) {
                gameTiles[i][j] = reverseRow[FIELD_WIDTH - 1 - j];
            }
        }

        if (needToAddTile) addTile();
    }

    public void up() {
        boolean needToAddTile = false;
        final Tile[] column = new Tile[FIELD_WIDTH];

        for (int j = 0; j < FIELD_WIDTH; j++) {
            for (int i = 0; i < FIELD_WIDTH; i++) {
                column[i] = gameTiles[i][j];
            }

            final boolean compressed = compressTiles(column);
            final boolean merged = mergeTiles(column);

            if (compressed || merged)
                needToAddTile = true;

            for (int i = 0; i < FIELD_WIDTH; i++) {
                gameTiles[i][j] = column[i];
            }
        }

        if (needToAddTile) addTile();
    }

    public void down() {
        boolean needToAddTile = false;
        final Tile[] reverseColumn = new Tile[FIELD_WIDTH];

        for (int j = 0; j < FIELD_WIDTH; j++) {
            for (int i = 0; i < FIELD_WIDTH; i++) {
                reverseColumn[i] = gameTiles[FIELD_WIDTH - 1 - i][j];
            }

            final boolean compressed = compressTiles(reverseColumn);
            final boolean merged = mergeTiles(reverseColumn);

            if (compressed || merged)
                needToAddTile = true;

            for (int i = 0; i < FIELD_WIDTH; i++) {
                gameTiles[i][j] = reverseColumn[FIELD_WIDTH - 1 - i];
            }
        }

        if (needToAddTile) addTile();
    }

    private void addTile() {
        final List<Tile> emptyTilesList = getEmptyTiles();
        if (emptyTilesList.isEmpty()) return;

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

    private boolean compressTiles(final Tile[] tiles) {
        final List<Tile> tilesList = new ArrayList<>();
        final List<Tile> emptyTilesList = new ArrayList<>();
        final int len = tiles.length;
        int firstEmptyTile = 4;

        for (int i = 0; i < len; i++) {
            final Tile tile = tiles[i];
            if (tile.isEmpty()) {
                emptyTilesList.add(tile);
                if (firstEmptyTile == 4)
                    firstEmptyTile = i;
            } else
                tilesList.add(tile);
        }

        boolean hasChanges = false;

        if (emptyTilesList.size() != (4 - firstEmptyTile)) {
            hasChanges = true;

            tilesList.addAll(emptyTilesList);

            for (int i = 0; i < len; i++) {
                tiles[i] = tilesList.get(i);
            }
        }

        return hasChanges;
    }

    private boolean mergeTiles(final Tile[] tiles) {
        if (tiles[0].isEmpty())
            return false;

        boolean hasChanges = false;

        final List<Tile> tilesList = new ArrayList<>(Arrays.asList(tiles));
        final int len = tiles.length;

        for (int i = 0; i < len - 1; i++) {
            final Tile current = tilesList.get(i);

            final Tile next = tilesList.get(i + 1);

            if (current.value == next.value && current.value != 0) {
                current.value *= 2;
                next.value = 0;
                tilesList.add(tilesList.remove(i + 1));

                hasChanges = true;
                score += current.value;
                if (current.value > maxTile)
                    maxTile = current.value;
            }
        }

        if (hasChanges) {
            for (int i = 0; i < len; i++) {
                tiles[i] = tilesList.get(i);
            }
        }

        return hasChanges;
    }

}
