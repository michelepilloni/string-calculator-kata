package srl.paros.stringcalc;

import srl.paros.stringcalc.exceptions.NegativeNumbersNotAllowedException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by michele.pilloni on 07/06/2017.
 */
public class StringCalculator {

    private final static String DEFAULT_DELIMITERS_REGEX = ",|\n";
    private final static String CUSTOM_DELIMITER_START_TOKEN = "//";
    private final static String CUSTOM_DELIMITER_END_TOKEN = "\n";
    private final static int IGNORE_THRESHOLD = 1000;

    public int add(String parameters) {
        if (parameters == null || parameters.isEmpty())
            return 0;

        List<Integer> numbersToAdd = getNumbersToAdd(parameters);

        List<Integer> negativeNumbers = numbersToAdd.stream().filter(nr -> nr < 0).collect(Collectors.toList());

        if (!negativeNumbers.isEmpty()) throw new NegativeNumbersNotAllowedException(negativeNumbers);

        int result = 0;

        for (int nr : numbersToAdd) {
            if (nr > IGNORE_THRESHOLD)
                continue;

            result += nr;
        }

        return result;
    }

    private List<Integer> getNumbersToAdd(String parameters) {
        List<Integer> result = new ArrayList<>();
        String[] splitted = getSplittedParameters(parameters);

        for (String currentNrStr : splitted) result.add(tryParseInt(currentNrStr));

        return result;
    }

    private String[] getSplittedParameters(String parameters) {
        if (!parameters.startsWith(CUSTOM_DELIMITER_START_TOKEN))
            return parameters.split(DEFAULT_DELIMITERS_REGEX);

        String delimiterPart = parameters.substring(parameters.indexOf(CUSTOM_DELIMITER_START_TOKEN) + CUSTOM_DELIMITER_START_TOKEN.length(), parameters.indexOf(CUSTOM_DELIMITER_END_TOKEN));
        String numbersPart = parameters.substring(parameters.indexOf(CUSTOM_DELIMITER_END_TOKEN) + CUSTOM_DELIMITER_END_TOKEN.length());
        return numbersPart.split(delimiterPart);
    }

    private int tryParseInt(String currentNrStr) {
        try {
            return Integer.parseInt(currentNrStr);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
