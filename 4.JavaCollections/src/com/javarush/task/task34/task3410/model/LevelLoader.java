package com.javarush.task.task34.task3410.model;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

import static com.javarush.task.task34.task3410.model.Model.FIELD_CELL_SIZE;

public class LevelLoader {
    private Path levels;

    public LevelLoader(final Path levels) {
        this.levels = levels;
    }

    public GameObjects getLevel(final int level) {
        final int halfFieldCellSize = FIELD_CELL_SIZE / 2;
        final Set<Wall> walls = new HashSet<>();
        final Set<Box> boxes = new HashSet<>();
        final Set<Home> homes = new HashSet<>();

        final Player player = new Player(halfFieldCellSize, 5 * halfFieldCellSize);
        boxes.add(new Box(3 * halfFieldCellSize, 5 * halfFieldCellSize));
        homes.add(new Home(5 * halfFieldCellSize, 5 * halfFieldCellSize));

        walls.add(new Wall(halfFieldCellSize, halfFieldCellSize));
        walls.add(new Wall(3 * halfFieldCellSize, halfFieldCellSize));
        walls.add(new Wall(5 * halfFieldCellSize, halfFieldCellSize));

        return new GameObjects(walls, boxes, homes, player);
    }
}
