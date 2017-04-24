package com.javarush.task.task37.task3708.retrievers;

import com.javarush.task.task37.task3708.cache.LRUCache;
import com.javarush.task.task37.task3708.storage.Storage;

public class CachingProxyRetriever implements Retriever {
    LRUCache<Long, Object> cache = new LRUCache<>(9);
    OriginalRetriever originalRetriever;

    public CachingProxyRetriever(final Storage storage) {
        originalRetriever = new OriginalRetriever(storage);
    }

    @Override
    public Object retrieve(final long id) {
        final Object value;
        if (cache.find(id) != null) {
            value = cache.find(id);
        } else {
            value = originalRetriever.retrieve(id);
            cache.set(id, value);
        }
        return value;
    }
}
