package com.javarush.task.task34.task3404;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class MathExpressionCalculationTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final Solution solution = new Solution();

    private final String expression;
    private final String expectedResult;
    private final String countOperations;
    private final String countNegativeNumbers;

    public MathExpressionCalculationTest(final String expression,
                                         final String expectedResult,
                                         final String countOperations,
                                         final String countNegativeNumbers) {
        this.expression = expression;
        this.expectedResult = expectedResult;
        this.countOperations = countOperations;
        this.countNegativeNumbers = countNegativeNumbers;
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() {
//        final List<Object []> list = new ArrayList<>();
//        list.add(new Object[] {"-5", "-5", "1", "1"});
//        return list;

        return Arrays.asList(new Object[][]{
                {"(-2)*(-5)", "10", "3", "2"},
                {"1 - 2 * (-2)", "5", "3", "1"},
                {"2/2*2", "2", "2", "0"},
                {"-5", "-5", "1", "1"},
                {"sin(2*(-5+1.5*4)+28)", "0.5", "6", "1"},
                {"sin(2*(-5+1.5*4)+28) - 1", "-0.5", "7", "1"},
                {"tan(45)", "1", "1", "0"},
                {"-sin(2*(-5+1.5*4)+28)", "-0.5", "7", "2"},
                {"(-1 + (-2))", "-3", "3", "2"},
                {"sin(100)-sin(100)", "0", "3", "0"},
                {"0.305", "0.3", "0", "0"},
                {"0.3051", "0.31", "0", "0"},
                {"1+(1+(1+1)*(1+1))*(1+1)+1", "12", "8", "0"},
                {"tan(44+sin(89-cos(180)^2))", "1", "6", "0"},
                {"-2+(-2+(-2)-2*(2+2))", "-14", "8", "3"},
                {"sin(80+(2+(1+1))*(1+1)+2)", "1", "7", "0"},
                {"1+4/2/2+2^2+2*2-2^(2-1+1)", "6", "11", "0"},
                {"2^10+2^(5+5)", "2048", "4", "0"},
                {"1.01+(2.02-1+1/0.5*1.02)/0.1+0.25+41.1", "72.96", "8", "0"},
                {"0.000025+0.000012", "0", "1", "0"},
                {"-2-(-2-1-(-2)-(-2)-(-2-2-(-2)-2)-2-2)", "-3", "16", "6"},
                {"cos(3 + 19*3)", "0.5", "3", "0"},
                {"2*(589+((2454*0.1548/0.01*(-2+9^2))+((25*123.12+45877*25)+25))-547)", "8302231.36", "14", "1"}
        });
    }
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    @Test
    public void testRecursion() throws Exception {
        solution.recursion(expression, 0);
        assertEquals(expectedResult + ' ' + countOperations, outContent.toString());
    }

    @Test
    public void testCountNegativeNumbers() throws Exception {
        assertEquals(Integer.parseInt(countNegativeNumbers), solution.countNegativeNumbers(expression));
    }
}