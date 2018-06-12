package com.vasidzius.tradevalidator.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CurrencyValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCurrency {
    String message() default "Currency doesn't match ISO 4217";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
