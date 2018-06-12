package com.vasidzius.tradevalidator.validation.rules.general.tradetype;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * The interface Valid trade type.
 */
@Documented
@Constraint(validatedBy = TradeTypeValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidTradeType {
    String message() default "Wrong trade type";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
