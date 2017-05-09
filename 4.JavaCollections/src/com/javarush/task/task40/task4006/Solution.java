package com.javarush.task.task40.task4006;

import java.io.*;
import java.net.Socket;
import java.net.URL;

/* 
Отправка GET-запроса через сокет
*/

public class Solution {
    public static void main(final String[] args) throws Exception {
        getSite(new URL("http://javarush.ru/social.html"));
    }

    public static void getSite(final URL url) {
        try {
            final Socket socket = new Socket(url.getHost(), url.getDefaultPort());
            final OutputStream os = socket.getOutputStream();
            final PrintWriter writer = new PrintWriter(os, true);

            writer.println("GET " + url.getPath() + " HTTP/1.1");
            writer.flush();

            final InputStream is = socket.getInputStream();
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            String responseLine;

            while ((responseLine = bufferedReader.readLine()) != null) {
                System.out.println(responseLine);
            }
            bufferedReader.close();

        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}