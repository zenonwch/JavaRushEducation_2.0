package com.javarush.task.task37.task3714;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class RomanToIntegerTest {

    @Test
    @Parameters(method = "positiveData")
    @TestCaseName("[{index}]: {0} == {1}")
    public void testPositiveRomanToIntegerConverter(final String romanNumber, final int integerNumber) throws Exception {
        assertEquals(integerNumber, Solution.romanToInteger(romanNumber));
    }

    @Test(expected = NumberFormatException.class)
    @Parameters(method = "negativeData")
    @TestCaseName("[{index}]: {0}")
    public void testNegativeRomanToIntegerConverter(final String romanNumber) throws Exception {
        Solution.romanToInteger(romanNumber);
    }

    private Object[] positiveData() {
        return new Object[]{
                new Object[]{"I", 1},
                new Object[]{"II", 2},
                new Object[]{"III", 3},
                new Object[]{"IV", 4},
                new Object[]{"V", 5},
                new Object[]{"VI", 6},
                new Object[]{"VII", 7},
                new Object[]{"VIII", 8},
                new Object[]{"IX", 9},
                new Object[]{"X", 10},
                new Object[]{"XI", 11},
                new Object[]{"XIII", 13},
                new Object[]{"XIV", 14},
                new Object[]{"XVIII", 18},
                new Object[]{"XIX", 19},
                new Object[]{"XX", 20},
                new Object[]{"XL", 40},
                new Object[]{"L", 50},
                new Object[]{"XC", 90},
                new Object[]{"C", 100},
                new Object[]{"CXXX", 130},
                new Object[]{"CCCXC", 390},
                new Object[]{"CD", 400},
                new Object[]{"D", 500},
                new Object[]{"DLV", 555},
                new Object[]{"CM", 900},
                new Object[]{"M", 1000},
                new Object[]{"MCMXCIX", 1999},
                new Object[]{"MM", 2000},
                new Object[]{"MMXVII", 2017}
        };
    }

    private Object[] negativeData() {
        return new Object[]{"A", "IVX", "VV", "XXXXX", "LL", "DD", "XD", "XM", "MXCIL"};
    }
}