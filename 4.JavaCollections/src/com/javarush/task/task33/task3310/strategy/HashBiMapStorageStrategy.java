package com.javarush.task.task33.task3310.strategy;

import com.google.common.collect.HashBiMap;

public class HashBiMapStorageStrategy implements StorageStrategy {
    private HashBiMap<Long, String> data = HashBiMap.create();

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
        return data.inverse().get(value);
    }

    @Override
    public String getValue(final Long key) {
        return data.get(key);
    }
}
