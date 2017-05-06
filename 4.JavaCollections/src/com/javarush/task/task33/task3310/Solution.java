package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution {
    public static Set<Long> getIds(final Shortener shortener, final Set<String> strings) {
        return strings.stream()
                .map(shortener::getId)
                .collect(Collectors.toSet());
    }

    public static Set<String> getStrings(final Shortener shortener, final Set<Long> keys) {
        return keys.stream()
                .map(shortener::getString)
                .collect(Collectors.toSet());
    }

    public static void testStrategy(final StorageStrategy strategy, final long elementsNumber) {
        final String strategyClassName = strategy.getClass().getSimpleName();
        Helper.printMessage(strategyClassName);

        final Set<String> strings = new HashSet<>();
        for (int i = 0; i < elementsNumber; i++) {
            strings.add(Helper.generateRandomString());
        }

        final Shortener shortener = new Shortener(strategy);

        final Long startGetIds = new Date().getTime();
        final Set<Long> ids = getIds(shortener, strings);
        final Long stopGetIds = new Date().getTime();
        final Long getIdsTime = stopGetIds - startGetIds;
        Helper.printMessage(getIdsTime.toString());

        final Long startGetStrings = new Date().getTime();
        final Set<String> collectedStrings = getStrings(shortener, ids);
        final Long stopGetStrings = new Date().getTime();
        final Long getStringTime = stopGetStrings - startGetStrings;
        Helper.printMessage(getStringTime.toString());

        Helper.printMessage(collectedStrings.equals(strings) ? "Тест пройден." : "Тест не пройден.");
    }

    public static void main(final String[] args) {
        testStrategy(new HashMapStorageStrategy(), 10000L);
        testStrategy(new OurHashMapStorageStrategy(), 10000L);
        testStrategy(new FileStorageStrategy(), 100L);
        testStrategy(new OurHashBiMapStorageStrategy(), 10000L);
        testStrategy(new HashBiMapStorageStrategy(), 10000L);
    }
}
