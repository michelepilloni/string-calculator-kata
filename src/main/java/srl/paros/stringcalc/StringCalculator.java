package srl.paros.stringcalc;

import srl.paros.stringcalc.exceptions.ExceptionsMessages;
import srl.paros.stringcalc.exceptions.MalformedParametersException;
import srl.paros.stringcalc.exceptions.NegativeNumbersNotAllowedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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

        int endTokenIndex = parameters.indexOf(CUSTOM_DELIMITER_END_TOKEN);
        if (endTokenIndex < 0)
            throw new MalformedParametersException(ExceptionsMessages.PARAMETERS_CUSTOM_DELIMITER_SEPARATOR_END_TOKEN_MISSING);

        String delimiterPart = parameters.substring(parameters.indexOf(CUSTOM_DELIMITER_START_TOKEN) + CUSTOM_DELIMITER_START_TOKEN.length(), endTokenIndex);
        String numbersPart = parameters.substring(endTokenIndex + CUSTOM_DELIMITER_END_TOKEN.length());

        if (delimiterPart.isEmpty())
            throw new MalformedParametersException(ExceptionsMessages.PARAMETERS_CUSTOM_DELIMITER_MISSING);

        return numbersPart.split(delimiterPart);
    }

    private int tryParseInt(String currentNrStr) {
        try {
            return Integer.parseInt(currentNrStr);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Please input numbers separated by ',' or '\\n'." +
                    "\n" +
                    "To specify a custom separator enclose it between // and \\n at the beginning (//[delimiter]\\n[numbers...])");

            String userInput = scanner.nextLine();

            if ("quit".equals(userInput.trim()) || "exit".equals(userInput.trim())) {
                System.out.println("Bye");
                break;
            }

            performStringCalculation(userInput.replace("\\n", "\n"));
        }
    }

    private static void performStringCalculation(String line) {
        try {
            System.out.printf("result: %s\n", new StringCalculator().add(line));
        } catch (Exception e) {
            System.out.printf("error computing result: %s\n", e.getMessage());
        }
    }
}
