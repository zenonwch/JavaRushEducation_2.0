package com.javarush.task.task34.task3413;

import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SoftCache {
    private final Map<Long, SoftReference<AnyObject>> cacheMap = new ConcurrentHashMap<>();

    public AnyObject get(final Long key) {
        final SoftReference<AnyObject> softReference = cacheMap.get(key);
        return softReference == null ? null : softReference.get();
    }

    public AnyObject put(final Long key, final AnyObject value) {

        final SoftReference<AnyObject> softReference = cacheMap.put(key, new SoftReference<>(value));
        final AnyObject anyObject;
        if (softReference != null) {
            anyObject = softReference.get();
            softReference.clear();
        } else {
            anyObject = null;
        }
        return anyObject;
    }

    public AnyObject remove(final Long key) {
        final SoftReference<AnyObject> softReference = cacheMap.remove(key);
        final AnyObject anyObject;
        if (softReference != null) {
            anyObject = softReference.get();
            softReference.clear();
        } else {
            anyObject = null;
        }
        return anyObject;
    }
}