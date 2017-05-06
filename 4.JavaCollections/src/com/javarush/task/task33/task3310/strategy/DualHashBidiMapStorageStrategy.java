package com.javarush.task.task33.task3310.strategy;

import org.apache.commons.collections4.bidimap.DualHashBidiMap;

public class DualHashBidiMapStorageStrategy implements StorageStrategy {
    private final DualHashBidiMap<Long, String> data = new DualHashBidiMap<>();

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
        return data.getKey(value);
    }

    @Override
    public String getValue(final Long key) {
        return data.get(key);
    }
}
