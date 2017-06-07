package srl.paros.stringcalc;

/**
 * Created by michele.pilloni on 07/06/2017.
 */
public class StringCalculator {

    public int add(String numbers) {
        if (numbers == null || numbers.isEmpty())
            return 0;

        String[] splitted = numbers.split(",");

        if (splitted.length > 2)
            throw new RuntimeException("The addition of more than two numbers is not supported");

        int result = 0;

        for (String currentNrStr : splitted) {
            int nr = Integer.parseInt(currentNrStr);
            result += nr;
        }

        return result;
    }

}
