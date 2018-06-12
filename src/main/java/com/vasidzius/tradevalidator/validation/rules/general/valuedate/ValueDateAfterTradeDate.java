package com.vasidzius.tradevalidator.validation.rules.general.valuedate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValueDateAfterTradeDateValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValueDateAfterTradeDate {
    String message() default "valueDate is before tradeDate";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
