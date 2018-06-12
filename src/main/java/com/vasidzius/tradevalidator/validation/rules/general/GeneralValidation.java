package com.vasidzius.tradevalidator.validation.rules.general;

import com.vasidzius.tradevalidator.validation.rules.general.valuedate.IsValueDateCurrencyHolidays;
import com.vasidzius.tradevalidator.validation.rules.general.valuedate.IsValueDateWeekend;
import com.vasidzius.tradevalidator.validation.rules.general.valuedate.TradeDateAfterCurrentDate;
import com.vasidzius.tradevalidator.validation.rules.general.valuedate.ValueDateAfterTradeDate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Documented
@ValueDateAfterTradeDate
@IsValueDateWeekend
@IsValueDateCurrencyHolidays
@TradeDateAfterCurrentDate
public @interface GeneralValidation {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
