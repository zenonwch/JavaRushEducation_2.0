package com.javarush.task.task39.task3903;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class SwapBitsTest {

    @Test
    @Parameters(method = "testData")
    @TestCaseName("[{index}]: {0} (swap bits {1} and {2}) => {3}")
    public void testSwapBits(final long number, final int i, final int j, final long expected) throws Exception {
        final long result = Solution.swapBits(number, i, j);
        assertEquals(expected, result);
    }

    private Object[] testData() {
        return new Object[]{
                new Object[]{2, 0, 1, 1},
                new Object[]{2, 1, 0, 1},
                new Object[]{1, 1, 0, 2},
                new Object[]{1, 0, 1, 2},
                new Object[]{2, 1, 3, 8},
                new Object[]{10000000002L, 1, 0, 10000000001L}
        };
    }
}