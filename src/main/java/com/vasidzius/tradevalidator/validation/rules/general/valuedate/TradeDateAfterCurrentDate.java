package com.vasidzius.tradevalidator.validation.rules.general.valuedate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TradeDateAfterCurrentDateValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TradeDateAfterCurrentDate {
    String message() default "tradeDate is after currentDate";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
