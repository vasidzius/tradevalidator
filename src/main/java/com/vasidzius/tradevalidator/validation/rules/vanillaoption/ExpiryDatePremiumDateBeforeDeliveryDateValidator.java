package com.vasidzius.tradevalidator.validation.rules.vanillaoption;

import com.vasidzius.tradevalidator.model.products.vanillaoption.VanillaOption;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class ExpiryDatePremiumDateBeforeDeliveryDateValidator implements ConstraintValidator<ExpiryDatePremiumDateBeforeDeliveryDate, VanillaOption> {

    @Override
    public boolean isValid(VanillaOption vanillaOption, ConstraintValidatorContext context) {
        LocalDate expiryDate = LocalDate.parse(vanillaOption.getExpiryDate());
        LocalDate premiumDate = LocalDate.parse(vanillaOption.getPremiumDate());
        LocalDate deliveryDate = LocalDate.parse(vanillaOption.getDeliveryDate());

        boolean validateExpiry = validate(context, expiryDate, deliveryDate);
        boolean validatePremium = validate(context, premiumDate, deliveryDate);
        return validateExpiry && validatePremium;
    }

    private boolean validate(ConstraintValidatorContext context, LocalDate date, LocalDate deliveryDate) {
        if (date.compareTo(deliveryDate) >= 0) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(String.format("expiry/premium date(%s) shall be before delivery date(%s)",
                    date, deliveryDate))
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
