package com.vasidzius.tradevalidator.validation.rules.spotforward;

import com.vasidzius.tradevalidator.model.TradeType;
import com.vasidzius.tradevalidator.model.products.spotforward.SpotForward;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class ValueDateMatchSpotForwardValidator implements ConstraintValidator<ValueDateMatchSpotForward, SpotForward> {

    @Override
    public boolean isValid(SpotForward spotForward, ConstraintValidatorContext context) {
        LocalDate tradeDate = LocalDate.parse(spotForward.getTradeDate());
        LocalDate valueDate = LocalDate.parse(spotForward.getValueDate());

        if (spotForward.getType() == TradeType.SPOT) {
            LocalDate correctValueDate = tradeDate.plusDays(2);
            if (!valueDate.equals(correctValueDate)) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(
                        String.format("Spot value date(%s) must be 2 days after trade date(%s), that is %s",
                                valueDate, tradeDate, correctValueDate))
                        .addConstraintViolation();
                return false;
            }
        }
        if (spotForward.getType() == TradeType.FORWARD) {
            LocalDate correctValueDate = tradeDate.plusDays(2);
            if (valueDate.compareTo(correctValueDate) <= 0) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(
                        String.format("Forward value date(%s) must be more than 2 days after trade date(%s), that is more than %s",
                                valueDate, tradeDate, correctValueDate))
                        .addConstraintViolation();
                return false;
            }
        }
        return true;
    }
}
