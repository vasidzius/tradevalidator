package com.vasidzius.tradevalidator.validation.rules.vanillaoption;

import com.vasidzius.tradevalidator.model.products.vanillaoption.StyleType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.stream.Collectors;

import static com.vasidzius.tradevalidator.model.products.vanillaoption.StyleType.WRONG_TYPE;

public class VanillaOptionStyleValidator implements ConstraintValidator<VanillaOptionStyle, StyleType> {
    @Override
    public boolean isValid(StyleType styleType, ConstraintValidatorContext context) {
        if (styleType == WRONG_TYPE) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(String.format(
                    "Wrong style type, must be one of %s",
                    Arrays.stream(StyleType.values())
                            .filter(type -> type != WRONG_TYPE)
                            .map(StyleType::toString)
                            .collect(Collectors.joining(", "))))
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
