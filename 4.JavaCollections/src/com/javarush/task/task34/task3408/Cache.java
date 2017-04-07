package com.javarush.task.task34.task3408;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;

public class Cache<K, V> {
    private final Map<K, V> cache = new WeakHashMap<>();

    public V getByKey(final K key, final Class<V> clazz) throws Exception {
        V obj = cache.get(key);
        if (obj == null) {
            obj = clazz.getConstructor(key.getClass()).newInstance(key);
            put(obj);
        }
        return obj;
    }

    public boolean put(final V obj) {
        try {
            final Method method = obj.getClass().getDeclaredMethod("getKey");
            method.setAccessible(true);
            final K key = (K) method.invoke(obj);
            cache.put(key, obj);
        } catch (final Exception ignored) {
            return false;
        }
        return true;
    }

    public int size() {
        return cache.size();
    }
}
