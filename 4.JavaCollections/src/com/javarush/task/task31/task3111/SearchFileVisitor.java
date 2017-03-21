package com.javarush.task.task31.task3111;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {
    private String partOfName;
    private String partOfContent;
    private int minSize = -1;
    private int maxSize = -1;
    private List<Path> foundFiles = new ArrayList<>();

    public void setPartOfName(final String partOfName) {
        this.partOfName = partOfName;
    }

    public void setPartOfContent(final String partOfContent) {
        this.partOfContent = partOfContent;
    }

    public void setMinSize(final int minSize) {
        this.minSize = minSize;
    }

    public void setMaxSize(final int maxSize) {
        this.maxSize = maxSize;
    }

    public List<Path> getFoundFiles() {
        return foundFiles;
    }

    @Override
    public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs) throws IOException {
        if (Files.isDirectory(file))
            return FileVisitResult.CONTINUE;

        boolean meetConditions = true;

        if (partOfName != null && !file.getFileName().toString().contains(partOfName))
            meetConditions = false;
        if (minSize >= 0 && !(Files.size(file) >= minSize))
            meetConditions = false;
        if (maxSize >= 0 && !(Files.size(file) <= maxSize))
            meetConditions = false;

        if (partOfContent != null) {
            final String data = new String(Files.readAllBytes(file));
            if (!data.contains(partOfContent))
                meetConditions = false;
        }

        if (meetConditions) foundFiles.add(file);
        return super.visitFile(file, attrs);
    }
}
