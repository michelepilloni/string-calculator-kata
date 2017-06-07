package srl.paros.stringcalc;

/**
 * Created by michele.pilloni on 07/06/2017.
 */
public class StringCalculator {

    private final static String DEFAULT_DELIMITERS = ",|\n";

    public int add(String numbers) {
        if (numbers == null || numbers.isEmpty())
            return 0;

        String[] splitted = getSplittedParameters(numbers);

        int result = 0;

        for (String currentNrStr : splitted) {
            int nr = tryParseInt(currentNrStr);
            result += nr;
        }

        return result;
    }

    private String[] getSplittedParameters(String parameters) {
        if (!parameters.startsWith("//"))
            return parameters.split(DEFAULT_DELIMITERS);

        String delimiterPart = parameters.substring(parameters.indexOf("//") + "//".length(), parameters.indexOf("\n"));
        String numbersPart = parameters.substring(parameters.indexOf("\n") + "\n".length());
        String[] result = numbersPart.split(delimiterPart);
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
