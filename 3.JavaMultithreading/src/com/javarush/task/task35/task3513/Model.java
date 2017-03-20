package com.javarush.task.task35.task3513;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Model {
    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles;
    private Stack<Tile[][]> previousStates = new Stack<>();
    private Stack<Integer> previousScores = new Stack<>();
    private boolean isSaveNeeded = true;

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

    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    public void left() {
        saveState(gameTiles);
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
        saveState(gameTiles);
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
        saveState(gameTiles);
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
        saveState(gameTiles);
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

    public void randomMove() {
        final int n = (int) (Math.random() * 100) % 4;
        if (n == 0) left();
        if (n == 1) right();
        if (n == 2) up();
        if (n == 3) down();
    }

    public MoveEfficiency getMoveEfficiency(Move move) {
        move.move();
        final int emptyTilesSize = getEmptyTiles().size();
        final MoveEfficiency efficiency = hasBoardChanged() ?
                new MoveEfficiency(emptyTilesSize, score, move) :
                new MoveEfficiency(-1, 0, move);
        rollback();
        return efficiency;
    }

    public boolean hasBoardChanged() {
        if (previousStates.isEmpty())
            return true;
        int sum1 = 0;
        int sum2 = 0;
        final Tile[][] lastInStack = previousStates.peek();
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                sum1 += gameTiles[i][j].value;
                sum2 += lastInStack[i][j].value;
            }
        }
        return sum1 != sum2;
    }

    public boolean canMove() {
        if (!getEmptyTiles().isEmpty())
            return true;

        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH - 1; j++) {
                if (gameTiles[i][j].value == gameTiles[i][j + 1].value)
                    return true;
            }
        }

        for (int i = 0; i < FIELD_WIDTH - 1; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if (gameTiles[i][j].value == gameTiles[i + 1][j].value)
                    return true;
            }
        }

        return false;
    }

    public void rollback() {
        if (previousScores.isEmpty() || previousStates.isEmpty())
            return;

        gameTiles = previousStates.pop();
        score = previousScores.pop();
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

    private void saveState(final Tile[][] tiles) {
        final Tile[][] copyTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                final Tile tile = new Tile();
                tile.value = tiles[i][j].value;
                copyTiles[i][j] = tile;
            }
        }
        previousStates.push(copyTiles);
        previousScores.push(score);
        isSaveNeeded = false;
    }

}
