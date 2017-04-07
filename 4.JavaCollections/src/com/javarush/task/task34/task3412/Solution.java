package com.javarush.task.task34.task3412;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/* 
Добавление логирования в класс
*/

public class Solution {
    private static final Logger logger = LoggerFactory.getLogger(Solution.class);

    private int value1;
    private String value2;
    private Date value3;

    public Solution(final int value1, final String value2, final Date value3) {
        logger.debug("constructor: values are: " + value1 + ", " + value2 + ", " + value3);
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
    }

    public static void main(final String[] args) {
        final Solution sol = new Solution(5, "test1", new Date());
        sol.printString();
        sol.printDateAsLong();
        sol.divide(5, 5);
        sol.divide(5, 0);
        sol.setValue1(10);
        sol.setValue2("test");
        sol.setValue3(new Date());
        sol.calculateAndSetValue3(5);
        sol.calculateAndSetValue3(Long.MAX_VALUE);
    }

    public void calculateAndSetValue3(long value) {
        logger.trace("Method calculateAndSetValue3() invoked");
        value -= 133;
        if (value > Integer.MAX_VALUE) {
            value1 = (int) (value / Integer.MAX_VALUE);
            logger.debug("calculateAndSetValue3: if input value > MAX Integer: value1 is: " + value1);
        } else {
            value1 = (int) value;
            logger.debug("calculateAndSetValue3: value1 is: " + value1);
        }
    }

    public void printString() {
        logger.trace("Method printString() invoked");
        if (value2 != null) {
            System.out.println(value2.length());
        }
    }

    public void printDateAsLong() {
        logger.trace("Method printDateAsLong() invoked");
        if (value3 != null) {
            System.out.println(value3.getTime());
        }
    }

    public void divide(final int number1, final int number2) {
        logger.trace("Method divide() invoked");
        try {
            System.out.println(number1 / number2);
        } catch (final ArithmeticException e) {
            logger.error("Method divide error: " + e.getMessage());
        }
    }

    public void setValue1(final int value1) {
        this.value1 = value1;
        logger.debug("setValue1: now value1 is: " + value1);
    }

    public void setValue2(final String value2) {
        this.value2 = value2;
        logger.debug("setValue2: now value2 is: " + value2);
    }

    public void setValue3(final Date value3) {
        this.value3 = value3;
        logger.debug("setValue3: now value3 is: " + value3);
    }
}
