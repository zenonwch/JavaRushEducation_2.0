package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.StorageStrategy;

public class Shortener {
    private Long lastId = 0L;
    private StorageStrategy storageStrategy;

    public Shortener(final StorageStrategy storageStrategy) {
        this.storageStrategy = storageStrategy;
    }

    public synchronized Long getId(final String string) {
        if (storageStrategy.containsValue(string)) {
            return storageStrategy.getKey(string);
        }
        storageStrategy.put(++lastId, string);
        return lastId;
    }

    public synchronized String getString(final Long id) {
        return storageStrategy.getValue(id);
    }
}
