package srl.paros.stringcalc;

import srl.paros.stringcalc.calculator.CalculationItem;
import srl.paros.stringcalc.calculator.Calculator;
import srl.paros.stringcalc.calculator.rules.IgnoreNumbersAbove;
import srl.paros.stringcalc.calculator.rules.ValidateOnlyPositiveNumbers;
import srl.paros.stringcalc.parser.ParameterParser;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Created by michele.pilloni on 07/06/2017.
 */
public class StringCalculator {

    public int add(String parameters) {
        List<Integer> numbers = ParameterParser.parse(parameters);
        List<CalculationItem> items = numbers.stream().map(CalculationItem::new).collect(Collectors.toList());
        return new Calculator(items).
                ignoreRule(new IgnoreNumbersAbove(1000)).
                validateRule(new ValidateOnlyPositiveNumbers()).
                add();
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

            int result = new StringCalculator().add(line);

            System.out.printf("result: %s\n", result);
        } catch (Exception e) {
            System.out.printf("error computing result: %s\n", e.getMessage());
        }
    }
}
