package com.vasidzius.tradevalidator.validation.rules.general.valuedate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IsValueDateWeekendValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsValueDateWeekend {
    String message() default "valueDate is weekend";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
