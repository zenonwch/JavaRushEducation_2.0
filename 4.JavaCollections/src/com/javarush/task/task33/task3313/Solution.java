package com.javarush.task.task33.task3313;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/* 
Сериализация даты в JSON
*/
public class Solution {
    public static void main(final String[] args) throws JsonProcessingException {
        final Event event = new Event("event#1");

        final String result = new ObjectMapper().writeValueAsString(event);

        System.out.println(result);
    }
}
