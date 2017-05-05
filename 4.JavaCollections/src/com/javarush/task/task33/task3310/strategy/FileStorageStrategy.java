package com.javarush.task.task33.task3310.strategy;

import java.util.Objects;
import java.util.stream.IntStream;

public class FileStorageStrategy implements StorageStrategy {
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final long DEFAULT_BUCKET_SIZE_LIMIT = 10000;
    private FileBucket[] table = new FileBucket[DEFAULT_INITIAL_CAPACITY];

    private long bucketSizeLimit = DEFAULT_BUCKET_SIZE_LIMIT;
    private int size;
    private long maxBucketSize;

    public FileStorageStrategy() {
        for (int i = 0; i < table.length; i++) {
            table[i] = new FileBucket();
        }
    }

    public long getBucketSizeLimit() {
        return bucketSizeLimit;
    }

    public void setBucketSizeLimit(final long bucketSizeLimit) {
        this.bucketSizeLimit = bucketSizeLimit;
    }

    public int getSize() {
        return size;
    }

    public long getMaxBucketSize() {
        return maxBucketSize;
    }

    private int hash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    private int indexFor(final int hash, final int length) {
        return hash & (length - 1);
    }

    private Entry getEntry(final Long key) {
        final int hash = (key == null) ? 0 : hash(key.hashCode());
        final int index = indexFor(hash, table.length);

        for (Entry e = table[index].getEntry(); e != null; e = e.next) {
            final Long eKey = e.key;
            if (e.hash == hash && Objects.equals(key, eKey))
                return e;
        }
        return null;
    }

    private void resize(final int newCapacity) {
        final FileBucket[] newTable = IntStream
                .range(0, newCapacity)
                .mapToObj(i -> new FileBucket())
                .toArray(FileBucket[]::new);
        transfer(newTable);
        table = newTable;
        maxBucketSize = 0;
        for (final FileBucket bucket : table) {
            final long currentBucketSize = bucket.getFileSize();
            maxBucketSize = currentBucketSize > maxBucketSize ? currentBucketSize : maxBucketSize;
        }
    }

    private void transfer(final FileBucket[] newTable) {
        for (final FileBucket bucket : table) {
            Entry e = bucket.getEntry();
            while (e != null) {
                final Entry next = e.next;
                final int index = indexFor(e.hash, newTable.length);
                e.next = newTable[index].getEntry();
                newTable[index].putEntry(e);
                e = next;
            }
            bucket.remove();
        }
    }

    private void addEntry(final int hash, final Long key, final String value, final int bucketIndex) {
        final Entry e = table[bucketIndex].getEntry();
        table[bucketIndex].putEntry(new Entry(hash, key, value, e));
        size++;

        final long currentBucketSize = table[bucketIndex].getFileSize();
        maxBucketSize = currentBucketSize > maxBucketSize ? currentBucketSize : maxBucketSize;
        if (maxBucketSize > bucketSizeLimit) resize(2 * table.length);
    }

    private void createEntry(final int hash, final Long key, final String value, final int bucketIndex) {
        table[bucketIndex].putEntry(new Entry(hash, key, value, null));
        size++;
        final long currentBucketSize = table[bucketIndex].getFileSize();
        maxBucketSize = currentBucketSize > maxBucketSize ? currentBucketSize : maxBucketSize;
    }

    @Override
    public boolean containsKey(final Long key) {
        return getEntry(key) != null;
    }

    @Override
    public boolean containsValue(final String value) {
        for (final FileBucket bucket : table) {
            for (Entry e = bucket.getEntry(); e != null; e = e.next) {
                if (Objects.equals(e.value, value))
                    return true;
            }
        }
        return false;
    }

    @Override
    public void put(final Long key, final String value) {
        final int hash = (key == null) ? 0 : hash(key.hashCode());
        final int index = indexFor(hash, table.length);

        if (table[index].getEntry() != null) {
            for (Entry e = table[index].getEntry(); e != null; e = e.next) {
                if (e.hash == hash && Objects.equals(e.key, key)) {
                    e.value = value;
                    return;
                }
            }
            addEntry(hash, key, value, index);
        } else {
            createEntry(hash, key, value, index);
        }
    }

    @Override
    public Long getKey(final String value) {
        for (final FileBucket bucket : table) {
            for (Entry e = bucket.getEntry(); e != null; e = e.next) {
                if (Objects.equals(e.value, value))
                    return e.key;
            }
        }
        return null;
    }

    @Override
    public String getValue(final Long key) {
        final Entry e = getEntry(key);
        return e == null ? null : e.value;
    }
}
