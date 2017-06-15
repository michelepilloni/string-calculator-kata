package srl.paros.stringcalc.calculator;

import srl.paros.stringcalc.calculator.rules.ValidateRule;
import srl.paros.stringcalc.calculator.rules.IgnoreRule;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by michele.pilloni on 15/06/2017.
 */
public class Calculator {

    List<CalculationItem> items;
    List<ValidateRule> validateRules = new ArrayList<>();
    List<IgnoreRule> ignoreRules = new ArrayList<>();

    public Calculator(List<CalculationItem> items) {
        this.items = items;
    }

    public Calculator validateRule(ValidateRule validateRule) {
        this.validateRules.add(validateRule);
        return this;
    }

    public Calculator ignoreRule(IgnoreRule ignoreRule) {
        this.ignoreRules.add(ignoreRule);
        return this;
    }

    public int add() {

        checkRules();

        int res = 0;
        for (CalculationItem item : items) res += item.getValue();
        return res;
    }

    private void checkRules() {
        for (ValidateRule validateRule : validateRules) {
            List<CalculationItem> notValidItems = items.stream().filter(item -> !validateRule.validate(item)).collect(Collectors.toList());
            if (!notValidItems.isEmpty()) {
                validateRule.handleNotValid(notValidItems);
            }
        }

        ignoreRules.forEach(rule -> items.removeIf(rule::ignore));
    }
}
