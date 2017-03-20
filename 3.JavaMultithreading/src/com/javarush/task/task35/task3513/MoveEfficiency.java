package com.javarush.task.task35.task3513;

public class MoveEfficiency implements Comparable<MoveEfficiency> {
    private int numberOfEmptyTiles;
    private int score;
    private Move move;

    public MoveEfficiency(final int numberOfEmptyTiles, final int score, final Move move) {
        this.numberOfEmptyTiles = numberOfEmptyTiles;
        this.score = score;
        this.move = move;
    }

    public Move getMove() {
        return move;
    }

    @Override
    public int compareTo (MoveEfficiency e) {
        if (e == null) return -1;
        if (numberOfEmptyTiles == e.numberOfEmptyTiles)
            return Integer.compare(score, e.score);
        return Integer.compare(numberOfEmptyTiles, e.numberOfEmptyTiles);
    }
}
