package com.javarush.task.task33.task3310.strategy;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HashMapStorageStrategy implements StorageStrategy {
    private HashMap<Long, String> data = new HashMap<>();

    @Override
    public boolean containsKey(final Long key) {
        return data.containsKey(key);
    }

    @Override
    public boolean containsValue(final String value) {
        return data.containsValue(value);
    }

    @Override
    public void put(final Long key, final String value) {
        data.put(key, value);
    }

    @Override
    public Long getKey(final String value) {
        return data.entrySet().stream()
                .filter(e -> Objects.equals(e.getValue(), value))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }

    @Override
    public String getValue(final Long key) {
        return data.get(key);
    }
}
