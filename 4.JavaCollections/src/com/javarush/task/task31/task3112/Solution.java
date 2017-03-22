package com.javarush.task.task31.task3112;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/* 
Загрузчик файлов
*/
public class Solution {

    public static void main(final String[] args) throws IOException {
        final Path passwords = downloadFile("https://www.amigo.com/ship/secretPassword.txt", Paths.get("C:/MyDownloads"));

        for (final String line : Files.readAllLines(passwords)) {
            System.out.println(line);
        }
    }

    public static Path downloadFile(final String urlString, final Path downloadDirectory) throws IOException {

        final URL url = new URL(urlString);
        final InputStream is = url.openStream();
        final Path tmpFile = Files.createTempFile("tmp-", "");
        Files.copy(is, tmpFile);
        is.close();

        final String fileName = urlString.substring(urlString.lastIndexOf('/') + 1);
        final Path dwnldPath = Paths.get(downloadDirectory + "/" + fileName);
        Files.move(tmpFile, dwnldPath);

        return dwnldPath;
    }
}
