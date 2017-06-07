package srl.paros.stringcalc;

/**
 * Created by michele.pilloni on 07/06/2017.
 */
public class StringCalculator {

    private final static String DEFAULT_DELIMITERS = ",|\n";
    private final static String CUSTOM_DELIMITER_START_TOKEN = "//";
    private final static String CUSTOM_DELIMITER_END_TOKEN = "\n";

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
        if (!parameters.startsWith(CUSTOM_DELIMITER_START_TOKEN))
            return parameters.split(DEFAULT_DELIMITERS);

        String delimiterPart = parameters.substring(parameters.indexOf(CUSTOM_DELIMITER_START_TOKEN) + CUSTOM_DELIMITER_START_TOKEN.length(), parameters.indexOf(CUSTOM_DELIMITER_END_TOKEN));
        String numbersPart = parameters.substring(parameters.indexOf(CUSTOM_DELIMITER_END_TOKEN) + CUSTOM_DELIMITER_END_TOKEN.length());
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
