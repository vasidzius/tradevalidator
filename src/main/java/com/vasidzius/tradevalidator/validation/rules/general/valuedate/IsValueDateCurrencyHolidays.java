package com.vasidzius.tradevalidator.validation.rules.general.valuedate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * The interface Is value date currency holidays.
 */
@Documented
@Constraint(validatedBy = IsValueDateCurrencyHolidaysValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsValueDateCurrencyHolidays {
    String message() default "valueDate is public holidays";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
