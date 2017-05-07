package com.javarush.task.task40.task4002;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;

/* 
Опять POST, а не GET
*/

public class Solution {
    public static void main(final String[] args) throws Exception {
        final Solution solution = new Solution();
        solution.sendPost("http://requestb.in/1naqv771", "name=zapp&mood=good&locale=&id=777");
    }

    public void sendPost(final String url, final String urlParameters) throws Exception {
        final HttpClient client = getHttpClient();
        final HttpPost request = new HttpPost(url);

        request.addHeader("User-Agent", "Mozilla/5.0");

        final List<NameValuePair> parameters = URLEncodedUtils.parse(urlParameters, Charset.defaultCharset());
        request.setEntity(new UrlEncodedFormEntity(parameters));

        final HttpResponse response = client.execute(request);

        System.out.println("Response Code: " + response.getStatusLine().getStatusCode());

        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        final StringBuffer result = new StringBuffer();
        String responseLine;
        while ((responseLine = bufferedReader.readLine()) != null) {
            result.append(responseLine);
        }

        System.out.println("Response: " + result.toString());
    }

    protected HttpClient getHttpClient() {
        return HttpClientBuilder.create().build();
    }
}
