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

    @Test
    public void moreThanTwoNumbersAddition() {
        int result = stringCalculator.add("1,9,6,7,3");

        assertEquals(1 + 9 + 6 + 7 + 3, result);
    }

    @Test
    public void handleAlsoNewlineAsDelimiter() {
        int result = stringCalculator.add("1\n2,3,7\n8\n9,5");

        assertEquals(1 + 2 + 3 + 7 + 8 + 9 + 5, result);
    }

    @Test
    public void supportAlsoCustomDelimiters() {
        int result = stringCalculator.add("//;\n1;2;9;7");

        assertEquals(1 + 2 + 9 + 7, result);
    }
}
