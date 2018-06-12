package com.vasidzius.tradevalidator.validation.rules.general.valuedate;

import com.vasidzius.tradevalidator.model.TradeInfo;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class ValueDateAfterTradeDateValidator implements ConstraintValidator<ValueDateAfterTradeDate, TradeInfo> {

    @Override
    public boolean isValid(TradeInfo tradeInfo, ConstraintValidatorContext context) {
        if (tradeInfo.getValueDate() != null) {
            LocalDate valueDate = LocalDate.parse(tradeInfo.getValueDate());
            LocalDate tradeDate = LocalDate.parse(tradeInfo.getTradeDate());
            return valueDate.compareTo(tradeDate) >= 0;
        }
        return true;
    }
}
