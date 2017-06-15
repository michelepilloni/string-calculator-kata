package srl.paros.stringcalc.calculator.rules;

import srl.paros.stringcalc.calculator.CalculationItem;

/**
 * Created by michele.pilloni on 15/06/2017.
 */
public class IgnoreNumbersAbove implements IgnoreRule {
    private final int thrshld;

    public IgnoreNumbersAbove(int thrshld) {
        this.thrshld = thrshld;
    }

    @Override
    public boolean ignore(CalculationItem calculationItem) {
        return calculationItem.getValue() > thrshld;
    }
}
