package com.javarush.task.task39.task3909;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class OneEditAwayTest {

    @Test
    @Parameters(method = "testData")
    @TestCaseName("[{index}]: {0} and {1}")
    public void testOneEditAway(final String first, final String second, final boolean expected) throws Exception {
        final boolean result = Solution.isOneEditAway(first, second);
        assertEquals(expected, result);
    }

    private Object[] testData() {
        return new Object[] {
                new Object[] {"ab", "acx", false},
                new Object[] {null, null, false},
                new Object[] {"", "", true},
                new Object[] {"a", "a", true},
                new Object[] {"abc", "abc", true},
                new Object[] {"a", "abc", false},
                new Object[] {"abc", "b", false},
                new Object[] {"abc", "adc", true},
                new Object[] {"ab", "abc", true},
                new Object[] {"bc", "abc", true},
                new Object[] {"ac", "abc", true},
                new Object[] {"abc", "ab", true},
                new Object[] {"abc", "bc", true},
                new Object[] {"abc", "ac", true},
                new Object[] {"abc", "cba", false},
                new Object[] {"abcd", "abd", true},
                new Object[] {"abcd", "abbb", false},
                new Object[] {"abbb", "bbba", false},
                new Object[] {"abcd", "avcx", false}

        };
    }
}