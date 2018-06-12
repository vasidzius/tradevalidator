package com.vasidzius.tradevalidator.validation.rules.general.tradetype;

import com.vasidzius.tradevalidator.model.TradeType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.stream.Collectors;

import static com.vasidzius.tradevalidator.model.TradeType.WRONG_TYPE;
import static com.vasidzius.tradevalidator.model.TradeType.values;

/**
 * The type Trade type validator validate 'type' field of TradeInfo according
 * allowed product list in the TradeType enum
 */
public class TradeTypeValidator implements ConstraintValidator<ValidTradeType, TradeType> {

    @Override
    public boolean isValid(TradeType tradeType, ConstraintValidatorContext context) {
        if (tradeType == WRONG_TYPE) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(String.format(
                    "Wrong trade type, must be one of %s",
                    Arrays.stream(values())
                            .filter(type -> type != WRONG_TYPE)
                            .map(TradeType::getValue)
                            .collect(Collectors.joining(", "))))
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
