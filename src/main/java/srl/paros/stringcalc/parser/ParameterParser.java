package srl.paros.stringcalc.parser;

import srl.paros.stringcalc.exceptions.ExceptionsMessages;
import srl.paros.stringcalc.exceptions.MalformedParametersException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michele.pilloni on 15/06/2017.
 */
public class ParameterParser {
    private final static String DEFAULT_DELIMITERS_REGEX = ",|\n";
    private final static String CUSTOM_DELIMITER_START_TOKEN = "//";
    private final static String CUSTOM_DELIMITER_END_TOKEN = "\n";

    public static List<Integer> parse(String parameters) {
        if (parameters == null || parameters.isEmpty()) {
            return new ArrayList<Integer>() {{
                add(0);
            }};
        }
        return getNumbersToAdd(parameters);
    }

    private static List<Integer> getNumbersToAdd(String parameters) {
        List<Integer> result = new ArrayList<>();
        String[] splitted = getSplittedParameters(parameters);

        for (String currentNrStr : splitted) result.add(tryParseInt(currentNrStr));

        return result;
    }

    private static int tryParseInt(String currentNrStr) {
        try {
            return Integer.parseInt(currentNrStr);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private static String[] getSplittedParameters(String parameters) {
        if (!parameters.startsWith(CUSTOM_DELIMITER_START_TOKEN))
            return parameters.split(DEFAULT_DELIMITERS_REGEX);

        int endTokenIndex = parameters.indexOf(CUSTOM_DELIMITER_END_TOKEN);
        if (endTokenIndex < 0)
            throw new MalformedParametersException(ExceptionsMessages.PARAMETERS_CUSTOM_DELIMITER_SEPARATOR_END_TOKEN_MISSING);

        String delimiterPart = parameters.substring(parameters.indexOf(CUSTOM_DELIMITER_START_TOKEN) + CUSTOM_DELIMITER_START_TOKEN.length(), endTokenIndex);
        String numbersPart = parameters.substring(endTokenIndex + CUSTOM_DELIMITER_END_TOKEN.length());

        if (delimiterPart.isEmpty())
            throw new MalformedParametersException(ExceptionsMessages.PARAMETERS_CUSTOM_DELIMITER_MISSING);

        return numbersPart.split(delimiterPart);
    }
}
