package com.vasidzius.tradevalidator.validation.rules.vanillaoption;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = VanillaOptionStyleValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface VanillaOptionStyle {
    String message() default "Wrong style type";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

