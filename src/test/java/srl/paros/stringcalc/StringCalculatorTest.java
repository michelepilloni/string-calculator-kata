package srl.paros.stringcalc;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by michele.pilloni on 07/06/2017.
 */

public class StringCalculatorTest {
    private StringCalculator stringCalculator;

    @Before
    public void init() {
        stringCalculator = new StringCalculator();
    }

    @Test
    public void whenEmptyStringReturnZero() {
        int result = stringCalculator.add("");

        assertEquals(0, result);
    }

    @Test
    public void oneNumberAddition() {
        int result = stringCalculator.add("1");

        assertEquals(1, result);
    }

    @Test
    public void twoNumbersAddition() {
        int result = stringCalculator.add("1,2");

        assertEquals(1 + 2, result);
    }

    @Test(expected = RuntimeException.class)
    public void whenMoreThanTwoParametersThrowException() throws Exception {
        int result = stringCalculator.add("1,2,3");
    }
}
