package com.javarush.task.task34.task3410.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import static com.javarush.task.task34.task3410.model.Model.FIELD_CELL_SIZE;

public class LevelLoader {
    private Path levels;

    public LevelLoader(final Path levels) {
        this.levels = levels;
    }

    public GameObjects getLevel(final int level) {
        final int currentLevel = (level - 1) % 60 + 1;

        final int halfFieldCellSize = FIELD_CELL_SIZE / 2;
        final Set<Wall> walls = new HashSet<>();
        final Set<Box> boxes = new HashSet<>();
        final Set<Home> homes = new HashSet<>();

        Player player = null;

        int mazeWidth = 0;
        int mazeHeight = 0;

        try (final BufferedReader reader = new BufferedReader(new FileReader(levels.toFile()))) {
            boolean foundLevelData = false;
            boolean foundLevelMaze = false;
            int lineNumber = 0;
            String line;

            while ((line = reader.readLine()) != null) {
                if (!line.equals("Maze: " + currentLevel) && !foundLevelData) continue;

                foundLevelData = true;

                if (line.contains("Size X:"))
                    mazeWidth = Integer.parseInt(line.substring("Size X: ".length()));

                if (line.contains("Size Y:"))
                    mazeHeight = Integer.parseInt(line.substring("Size Y: ".length()));

                if (line.isEmpty()) {
                    foundLevelMaze = !foundLevelMaze;
                    if (!foundLevelMaze) {
                        lineNumber = 0;
                        foundLevelData = false;
                    }
                    continue;
                }

                if (foundLevelMaze) {
                    final int oY = lineNumber++ * FIELD_CELL_SIZE + halfFieldCellSize;
                    for (int j = 0; j < mazeWidth; j++) {
                        final int oX = j * FIELD_CELL_SIZE + halfFieldCellSize;

                        switch (line.charAt(j)) {
                            case 'X':
                                walls.add(new Wall(oX, oY));
                                break;
                            case '*':
                                boxes.add(new Box(oX, oY));
                                break;
                            case '.':
                                homes.add(new Home(oX, oY));
                                break;
                            case '&':
                                boxes.add(new Box(oX, oY));
                                homes.add(new Home(oX, oY));
                                break;
                            case '@':
                                player = new Player(oX, oY);
                        }
                    }
                }
            }
        } catch (final IOException ignored) {
        }

        return new GameObjects(walls, boxes, homes, player);
    }
}
