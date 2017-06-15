package srl.paros.stringcalc.calculator.rules;

import srl.paros.stringcalc.calculator.CalculationItem;
import srl.paros.stringcalc.exceptions.NegativeNumbersNotAllowedException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by michele.pilloni on 15/06/2017.
 */
public class ValidateOnlyPositiveNumbers implements ValidateRule {
    @Override
    public boolean validate(CalculationItem calculationItem) {
        return calculationItem.getValue() >= 0;
    }

    @Override
    public void handleNotValid(List<CalculationItem> notValidItems) {
        throw new NegativeNumbersNotAllowedException(notValidItems.stream().map(CalculationItem::getValue).collect(Collectors.toList()));
    }
}
