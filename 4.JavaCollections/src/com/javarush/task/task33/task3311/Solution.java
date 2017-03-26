package com.javarush.task.task33.task3311;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;

/* 
Странная ошибка
*/
public class Solution {
    public static void main(final String[] args) throws IOException {
        final StringWriter stringWriter = new StringWriter();
        final ObjectMapper objectMapper = new ObjectMapper();
        final String sampleJsonString = "{\"id\":1,\"name\":\"first\",\"KEY#1\":\"VALUE#1\",\"KEY#3\":\"VALUE#3\",\"KEY#2\":\"VALUE#2\"}";
        final RealBean realBean = objectMapper.readValue(sampleJsonString, RealBean.class);

        objectMapper.writeValue(stringWriter, realBean);
        System.out.println(stringWriter.toString());
    }
}
