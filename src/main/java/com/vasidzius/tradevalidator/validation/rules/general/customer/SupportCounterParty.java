package com.vasidzius.tradevalidator.validation.rules.general.customer;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * The interface Support counter party.
 */
@Documented
@Constraint(validatedBy = SupportCounterPartyValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SupportCounterParty {
    String message() default "Counter party (customer) must be one of the supported ones";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
