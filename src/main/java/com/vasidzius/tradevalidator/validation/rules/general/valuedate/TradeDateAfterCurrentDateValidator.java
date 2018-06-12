package com.vasidzius.tradevalidator.validation.rules.general.valuedate;

import com.vasidzius.tradevalidator.model.TradeInfo;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class TradeDateAfterCurrentDateValidator implements ConstraintValidator<TradeDateAfterCurrentDate, TradeInfo> {
    @Override
    public boolean isValid(TradeInfo tradeInfo, ConstraintValidatorContext context) {
        LocalDate currentDate = tradeInfo.getCurrentDate();
        LocalDate tradeDate = LocalDate.parse(tradeInfo.getTradeDate());
        boolean result = tradeDate.compareTo(currentDate) >= 0;
        if (!result) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(String.format(
                    "tradeDate(%s) is not after currentDate(%s)", tradeDate, currentDate
            )).addConstraintViolation();
        }
        return result;
    }
}
