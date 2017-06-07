package srl.paros.stringcalc.exceptions;

import java.util.List;

/**
 * Created by michele.pilloni on 08/06/2017.
 */
public class NegativeNumbersNotAllowedException extends MalformedParametersException {
    public NegativeNumbersNotAllowedException(List<Integer> negativeNumbers) {
        super(initExceptionMessage(negativeNumbers));
    }

    static String initExceptionMessage(List<Integer> negativeNumbers) {
        String exMsg = "";
        for (int i = 0; i < negativeNumbers.size(); i++) {
            Integer negativeNumber = negativeNumbers.get(i);
            exMsg += negativeNumber;
            if (i != negativeNumbers.size() - 1)
                exMsg += ", ";
        }
        return exMsg;
    }
}
