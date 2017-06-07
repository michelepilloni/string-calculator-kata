package srl.paros.stringcalc;

import srl.paros.stringcalc.exceptions.ExceptionsMessages;
import srl.paros.stringcalc.exceptions.MalformedParametersException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by michele.pilloni on 07/06/2017.
 */
public class StringCalculator {

    private final static String DEFAULT_DELIMITERS = ",|\n";
    private final static String CUSTOM_DELIMITER_START_TOKEN = "//";
    private final static String CUSTOM_DELIMITER_END_TOKEN = "\n";

    public int add(String parameters) {
        if (parameters == null || parameters.isEmpty())
            return 0;

        List<Integer> numbersToAdd = getNumbersToAdd(parameters);

        List<Integer> negativeNumbers = numbersToAdd.stream().filter(nr -> nr < 0).collect(Collectors.toList());

        if (!negativeNumbers.isEmpty()) {
            String exMsg = "";
            for (int i = 0; i < negativeNumbers.size(); i++) {
                Integer negativeNumber = negativeNumbers.get(i);
                exMsg += negativeNumber;
                if (i != negativeNumbers.size() - 1)
                    exMsg += ", ";
            }
            throw new MalformedParametersException(String.format(ExceptionsMessages.PARAMETERS_NEGATIVES_NOT_ALLOWED, exMsg));
        }

        int result = 0;

        for (int nr : numbersToAdd) result += nr;

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
