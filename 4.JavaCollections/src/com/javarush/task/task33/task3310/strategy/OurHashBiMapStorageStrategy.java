package com.javarush.task.task33.task3310.strategy;

import java.util.HashMap;

public class OurHashBiMapStorageStrategy implements StorageStrategy {
    private final HashMap<Long, String> k2v = new HashMap<>();
    private final HashMap<String, Long> v2k = new HashMap<>();

    @Override
    public boolean containsKey(final Long key) {
        return k2v.containsKey(key);
    }

    @Override
    public boolean containsValue(final String value) {
        return v2k.containsKey(value);
    }

    @Override
    public void put(final Long key, final String value) {
        k2v.put(key, value);
        v2k.put(value, key);
    }

    @Override
    public Long getKey(final String value) {
        return v2k.get(value);
    }

    @Override
    public String getValue(final Long key) {
        return k2v.get(key);
    }
}
