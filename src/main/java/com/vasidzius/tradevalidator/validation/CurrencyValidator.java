package com.vasidzius.tradevalidator.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

public class CurrencyValidator implements ConstraintValidator<ValidCurrency, String> {

    private List<String> validCurrencies;

    @Override
    public void initialize(ValidCurrency constraintAnnotation) {

        validCurrencies = Arrays.asList("EUR", "USD");
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return validCurrencies.contains(value);
//        return true;
    }
}
