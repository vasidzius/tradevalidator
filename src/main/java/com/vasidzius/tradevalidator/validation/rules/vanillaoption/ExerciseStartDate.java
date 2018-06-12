package com.vasidzius.tradevalidator.validation.rules.vanillaoption;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ExerciseStartDateValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExerciseStartDate {
    String message() default "valueDate must match Spot, Forward types";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
