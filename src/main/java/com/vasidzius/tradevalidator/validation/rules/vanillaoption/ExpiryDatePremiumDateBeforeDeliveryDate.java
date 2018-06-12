package com.vasidzius.tradevalidator.validation.rules.vanillaoption;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ExpiryDatePremiumDateBeforeDeliveryDateValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExpiryDatePremiumDateBeforeDeliveryDate {
    String message() default "expiry date and premium date shall be before delivery date";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
