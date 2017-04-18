package com.javarush.task.task36.task3610;

import java.io.Serializable;
import java.util.*;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {
    static final long serialVersionUID = 123456789L;
    private HashMap<K, List<V>> map;
    private int repeatCount;

    public MyMultiMap(final int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    @Override
    public int size() {
        return values().size();
    }

    @Override
    public V put(final K key, final V value) {
        List<V> list = new LinkedList<>();
        V prevLastValue = null;
        if (map.get(key) != null) {
            list = map.get(key);
            prevLastValue = list.get(list.size() - 1);
            if (list.size() == repeatCount) {
                list.remove(0);
            }
        }
        list.add(value);
        map.put(key, list);
        return prevLastValue;
    }

    @Override
    public V remove(final Object key) {
        V value = null;
        if (map.get(key) == null || map.get(key).isEmpty()) {
            map.remove(key);
        } else if (map.get(key).size() == 1) {
            value = map.get(key).get(0);
            map.remove(key);
        } else {
            value = map.get(key).remove(0);
        }
        return value;
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        return map.values().stream().collect(ArrayList::new, List::addAll, List::addAll);
    }

    @Override
    public boolean containsKey(final Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(final Object value) {
        return values().contains(value);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        for (final Map.Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append('=');
            for (final V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        final String substring = sb.substring(0, sb.length() - 2);
        return substring + '}';
    }
}