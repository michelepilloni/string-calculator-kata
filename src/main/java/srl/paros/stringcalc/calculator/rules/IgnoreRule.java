package srl.paros.stringcalc.calculator.rules;

import srl.paros.stringcalc.calculator.CalculationItem;

/**
 * Created by michele.pilloni on 15/06/2017.
 */
public interface IgnoreRule {
    boolean ignore(CalculationItem calculationItem);
}
