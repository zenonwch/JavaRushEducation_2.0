package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SpeedTest {

    @Test
    public void testHashMapStorage() {
        final HashMapStorageStrategy hashMapStorageStrategy = new HashMapStorageStrategy();
        final HashBiMapStorageStrategy hashBiMapStorageStrategy = new HashBiMapStorageStrategy();
        final Shortener shortener1 = new Shortener(hashMapStorageStrategy);
        final Shortener shortener2 = new Shortener(hashBiMapStorageStrategy);

        final Set<String> origStrings = IntStream.range(0, 10000)
                .mapToObj(i -> Helper.generateRandomString())
                .collect(Collectors.toSet());

        final Set<Long> ids = new HashSet<>();

        final long timeForGettingIds1 = getTimeForGettingIds(shortener1, origStrings, ids);
        final long timeForGettingIds2 = getTimeForGettingIds(shortener2, origStrings, ids);

        Assert.assertTrue(timeForGettingIds1 > timeForGettingIds2);

        final Set<String> resultStrings = new HashSet<>();

        final long timeForGettingStrings1 = getTimeForGettingStrings(shortener1, ids, resultStrings);
        final long timeForGettingStrings2 = getTimeForGettingStrings(shortener2, ids, resultStrings);

        Assert.assertEquals(timeForGettingStrings1, timeForGettingStrings2, 30f);
    }

    public long getTimeForGettingIds(final Shortener shortener, final Set<String> strings, final Set<Long> ids) {
        final long startTime = new Date().getTime();
        for (final String s : strings) {
            final Long id = shortener.getId(s);
            ids.add(id);
        }
        final long endTime = new Date().getTime();
        return endTime - startTime;
    }

    public long getTimeForGettingStrings(final Shortener shortener, final Set<Long> ids, final Set<String> strings) {
        final long startTime = new Date().getTime();
        for (final long id : ids) {
            final String s = shortener.getString(id);
            strings.add(s);
        }
        final long endTime = new Date().getTime();
        return endTime - startTime;
    }
}
