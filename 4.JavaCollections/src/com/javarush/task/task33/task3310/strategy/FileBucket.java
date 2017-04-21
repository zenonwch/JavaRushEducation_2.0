package com.javarush.task.task33.task3310.strategy;

import com.javarush.task.task33.task3310.ExceptionHandler;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket {
    private Path path;

    public FileBucket() {
        try {
            path = Files.createTempFile("FileBucket_", ".tmp");
            Files.deleteIfExists(path);
            Files.createFile(path);
            path.toFile().deleteOnExit();
        } catch (final IOException e) {
            ExceptionHandler.log(e);
        }
    }

    public long getFileSize() {
        long size = 0;
        try {
            size = Files.size(path);
        } catch (final IOException e) {
            ExceptionHandler.log(e);
        }
        return size;
    }

    public void putEntry(final Entry entry) {
        try (final ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path))) {
            oos.writeObject(entry);
        } catch (final IOException e) {
            ExceptionHandler.log(e);
        }
    }

    public Entry getEntry() {
        if (getFileSize() == 0) return null;

        Entry entry = null;
        try (final ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(path))) {
            entry = (Entry) ois.readObject();
        } catch (final ClassNotFoundException | IOException e) {
            ExceptionHandler.log(e);
        }
        return entry;
    }

    public void remove() {
        try {
            Files.delete(path);
        } catch (final IOException e) {
            ExceptionHandler.log(e);
        }
    }
}
