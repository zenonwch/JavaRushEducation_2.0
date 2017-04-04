package com.javarush.task.task34.task3411;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class HanoiTowersTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private final int count;
    private final int expectedCountOperations;

    public HanoiTowersTest(final int count) {
        this.count = count;
        expectedCountOperations = (int) StrictMath.pow(2, count) - 1;
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Integer> data() {
        final List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        return list;
    }

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Test
    public void testMoveRing() throws Exception {
        Solution.moveRing('A', 'B', 'C', count);
        final int result = outContent.size() == 0 ? 0 : outContent.toString().split(System.lineSeparator()).length;
        assertEquals(expectedCountOperations, result);
    }
}