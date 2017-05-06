package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.*;
import org.junit.Assert;
import org.junit.Test;

public class FunctionalTest {

    public void testStorage(final Shortener shortener) throws Exception {
        final String s1 = Helper.generateRandomString();
        final String s2 = Helper.generateRandomString();
        final String s3 = s1;

        final Long id1 = shortener.getId(s1);
        final Long id2 = shortener.getId(s2);
        final Long id3 = shortener.getId(s3);

        Assert.assertNotEquals(id2, id1);
        Assert.assertNotEquals(id2, id3);
        Assert.assertEquals(id1, id3);

        final String getS1 = shortener.getString(id1);
        final String getS2 = shortener.getString(id2);
        final String getS3 = shortener.getString(id3);

        Assert.assertEquals(s1, getS1);
        Assert.assertEquals(s2, getS2);
        Assert.assertEquals(s3, getS3);
    }

    @Test
    public void testHashMapStorageStrategy() throws Exception {
        final HashMapStorageStrategy hashMapStorageStrategy  = new HashMapStorageStrategy();
        final Shortener shortener = new Shortener(hashMapStorageStrategy );
        testStorage(shortener);
    }

    @Test
    public void testOurHashMapStorageStrategy() throws Exception {
        final OurHashMapStorageStrategy ourHashMapStorageStrategy = new OurHashMapStorageStrategy();
        final Shortener shortener = new Shortener(ourHashMapStorageStrategy);
        testStorage(shortener);
    }

    @Test
    public void testFileStorageStrategy() throws Exception {
        final FileStorageStrategy fileStorageStrategy = new FileStorageStrategy();
        final Shortener shortener = new Shortener(fileStorageStrategy);
        testStorage(shortener);
    }

    @Test
    public void testHashBiMapStorageStrategy() throws Exception {
        final HashBiMapStorageStrategy hashBiMapStorageStrategy = new HashBiMapStorageStrategy();
        final Shortener shortener = new Shortener(hashBiMapStorageStrategy);
        testStorage(shortener);
    }

    @Test
    public void testDualHashBidiMapStorageStrategy() throws Exception {
        final DualHashBidiMapStorageStrategy dualHashBidiMapStorageStrategy = new DualHashBidiMapStorageStrategy();
        final Shortener shortener = new Shortener(dualHashBidiMapStorageStrategy);
        testStorage(shortener);
    }

    @Test
    public void testOurHashBiMapStorageStrategy() throws Exception {
        final OurHashBiMapStorageStrategy ourHashBiMapStorageStrategy = new OurHashBiMapStorageStrategy();
        final Shortener shortener = new Shortener(ourHashBiMapStorageStrategy);
        testStorage(shortener);
    }

}
