package com.javarush.task.task33.task3310.strategy;

public class OurHashMapStorageStrategy implements StorageStrategy {
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private Entry[] table = new Entry[DEFAULT_INITIAL_CAPACITY];
    private int size;
    private int threshold = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
    private float loadFactor = DEFAULT_LOAD_FACTOR;

    public int hash(final Long k) {
        final int h;
        return (k == null) ? 0 : (h = k.hashCode()) ^ (h >>> 16);
    }

    public int indexFor(final int hash, final int length) {
        return hash & (length - 1);
    }

    public Entry getEntry(final Long key) {
        final int hash = (key == null) ? 0 : hash((long) key.hashCode());
        for (Entry e = table[indexFor(hash, table.length)]; e != null; e = e.next) {
            final Long eKey = e.getKey();
            if (e.hash == hash &&
                    (eKey == key || (eKey != null && eKey.equals(key))))
                return e;
        }
        return null;
    }

    public void resize(final int newCapacity) {
        final Entry[] newTable = new Entry[newCapacity];
        transfer(newTable);
        table = newTable;
        threshold = (int) (newCapacity * loadFactor);
    }

    public void transfer(final Entry[] newTable) {
        final Entry[] oldTable = table;
        for (int i = 0; i < oldTable.length; i++) {
            Entry e = oldTable[i];
            if (e != null) {
                oldTable[i] = null;
                do {
                    final Entry next = e.next;
                    final int j = indexFor(e.hash, newTable.length);
                    e.next = newTable[j];
                    newTable[j] = e;
                    e = next;
                } while (e != null);
            }
        }
    }

    public void addEntry(final int hash, final Long key, final String value, final int bucketIndex) {
        final Entry e = table[bucketIndex];
        table[bucketIndex] = new Entry(hash, key, value, e);
        if (size++ >= threshold) resize(2 * table.length);
    }

    public void createEntry(final int hash, final Long key, final String value, final int bucketIndex) {
        final Entry e = table[bucketIndex];
        table[bucketIndex] = new Entry(hash, key, value, e);
        size++;
    }

    @Override
    public boolean containsKey(final Long key) {
        return getEntry(key) != null;
    }

    @Override
    public boolean containsValue(final String value) {
        if (table.length == 0) return false;
        for (Entry e : table) {
            if (e == null) {
                for (int i = 0; i < table.length; i++) {
                    for (e = table[i]; e != null; e = e.next) {
                        if (e.value == null) return true;
                    }
                }
                return false;
            }
            final String eValue = e.getValue();
            if (value.equals(eValue))
                return true;
        }
        return false;
    }

    @Override
    public void put(final Long key, final String value) {
        final int hash = (key == null) ? 0 : hash((long) key.hashCode());
        final int i = indexFor(hash, table.length);

        for (Entry e = table[i]; e != null; e = e.next) {
            final Long eKey = e.getKey();
            if (eKey == key ||
                    (eKey != null && eKey.equals(key))) {
                e.value = value;
                return;
            }
        }

        addEntry(hash, key, value, i);
    }

    @Override
    public Long getKey(final String value) {
        for (final Entry e : table) {
            final String eValue = e.getValue();
            if (eValue == value ||
                    (eValue != null && eValue.equals(value)))
                return e.getKey();
        }
        return null;
    }

    @Override
    public String getValue(final Long key) {
        return getEntry(key).getValue();
    }
}
