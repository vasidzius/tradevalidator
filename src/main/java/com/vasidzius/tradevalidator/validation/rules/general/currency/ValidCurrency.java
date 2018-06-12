package com.vasidzius.tradevalidator.validation.rules.general.currency;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * The interface Valid currency.
 */
@Documented
@Constraint(validatedBy = CurrencyValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCurrency {
    String message() default "Currency doesn't match ISO 4217";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
