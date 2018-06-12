package com.vasidzius.tradevalidator.validation.rules.vanillaoption;

import com.vasidzius.tradevalidator.model.products.vanillaoption.VanillaOption;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

import static com.vasidzius.tradevalidator.model.products.vanillaoption.StyleType.AMERICAN;

public class ExerciseStartDateValidator implements ConstraintValidator<ExerciseStartDate, VanillaOption> {

    @Override
    public boolean isValid(VanillaOption vanillaOption, ConstraintValidatorContext context) {
        if (vanillaOption.getStyle() == AMERICAN) {
            String exercStartDateString = vanillaOption.getExerciseStartDate();
            if (exercStartDateString == null) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("For AMERICAN style VANILLA_OPTION must have exerciseStartDate")
                        .addConstraintViolation();
                return false;
            } else {
                LocalDate exerciseStartDate = LocalDate.parse(exercStartDateString);
                LocalDate tradeDate = LocalDate.parse(vanillaOption.getTradeDate());
                LocalDate expireDate = LocalDate.parse(vanillaOption.getExpiryDate());
                if (exerciseStartDate.compareTo(tradeDate) <= 0 || exerciseStartDate.compareTo(expireDate) >= 0) {
                    context.disableDefaultConstraintViolation();
                    context.buildConstraintViolationWithTemplate(String.format(
                            "the exerciseStartDate(%s), which has to be after the trade date(%s) but before the expiry date(%s)",
                            exerciseStartDate, tradeDate, expireDate
                    ))
                            .addConstraintViolation();
                    return false;
                }
            }
        }
        return true;
    }
}
