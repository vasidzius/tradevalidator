package com.vasidzius.tradevalidator.validation.rules.general.valuedate;

import com.vasidzius.tradevalidator.model.TradeInfo;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class IsValueDateWeekendValidator implements ConstraintValidator<IsValueDateWeekend, TradeInfo> {

    @Override
    public boolean isValid(TradeInfo tradeInfo, ConstraintValidatorContext context) {
        if (tradeInfo.getValueDate() != null) {
            LocalDate valueDate = LocalDate.parse(tradeInfo.getValueDate());
            return valueDate.getDayOfWeek() != DayOfWeek.SATURDAY &&
                    valueDate.getDayOfWeek() != DayOfWeek.SUNDAY;
        }
        return true;
    }
}
