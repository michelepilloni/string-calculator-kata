package srl.paros.stringcalc;

/**
 * Created by michele.pilloni on 07/06/2017.
 */
public class StringCalculator {

    public int add(String numbers) {
        if (numbers == null || numbers.isEmpty())
            return 0;

        String[] splitted = numbers.split(",");

        int result = 0;

        for (String currentNrStr : splitted) {
            int nr = tryParseInt(currentNrStr);
            result += nr;
        }

        return result;
    }

    private int tryParseInt(String currentNrStr) {
        try {
            return Integer.parseInt(currentNrStr);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

}
