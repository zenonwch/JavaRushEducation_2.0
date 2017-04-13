package com.javarush.task.task35.task3507;

import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class ClassLoaderTest {
    @Test
    public void testGetAllAnimals() throws Exception {
        final Set<? extends Animal> allAnimals = Solution.getAllAnimals(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        assertEquals(1, allAnimals.size());
    }

}