package srl.paros.stringcalc.calculator.rules;

import srl.paros.stringcalc.calculator.CalculationItem;

import java.util.List;

/**
 * Created by michele.pilloni on 15/06/2017.
 */
public interface ValidateRule {
    boolean validate(CalculationItem calculationItem);

    void handleNotValid(List<CalculationItem> notValidItems);
}
