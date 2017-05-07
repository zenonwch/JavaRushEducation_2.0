package com.javarush.task.task40.task4001;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/* 
POST, а не GET
*/

public class Solution {
    public static void main(final String[] args) throws Exception {
        final Solution solution = new Solution();
        solution.sendPost(new URL("http://requestb.in/172q3ke1"), "name=zapp&mood=good&locale=&id=777");
    }

    public void sendPost(final URL url, final String urlParameters) throws Exception {
        final HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");

        final OutputStream outputStream = connection.getOutputStream();
        final BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write(urlParameters);
        bufferedWriter.flush();
        bufferedWriter.close();

        final int responseCode = connection.getResponseCode();
        System.out.println("Response Code: " + responseCode);

        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String responseLine;
        final StringBuilder response = new StringBuilder();

        while ((responseLine = bufferedReader.readLine()) != null) {
            response.append(responseLine);
        }
        bufferedReader.close();

        System.out.println("Response: " + response.toString());
    }
}
