package com.vasidzius.tradevalidator.validation.rules.vanillaoption;

import com.vasidzius.tradevalidator.model.TradeType;
import com.vasidzius.tradevalidator.model.products.vanillaoption.VanillaOption;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.validation.ConstraintValidatorContext;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(JUnitParamsRunner.class)
public class ExpiryDatePremiumDateBeforeDeliveryDateValidatorTest {

    private ExpiryDatePremiumDateBeforeDeliveryDateValidator validator;

    @Before
    public void init() {
        validator = new ExpiryDatePremiumDateBeforeDeliveryDateValidator();
        validator.initialize(mock(ExpiryDatePremiumDateBeforeDeliveryDate.class));
    }

    @Test
    @Parameters({
            "2018-06-13, 2018-06-13, 2018-06-13",
            "2018-06-13, 2018-06-15, 2018-06-13",
            "2018-06-15, 2018-06-13, 2018-06-13",
            "2018-06-15, 2018-06-15, 2018-06-13",
    })
    public void failed(String expiryDate, String premiumDate, String deliveryDate) {
        VanillaOption vanillaOption = new VanillaOption();
        vanillaOption.setType(TradeType.VANILLA_OPTION);
        vanillaOption.setPremiumDate(premiumDate);
        vanillaOption.setExpiryDate(expiryDate);
        vanillaOption.setDeliveryDate(deliveryDate);
        ConstraintValidatorContext context = mock(ConstraintValidatorContext.class, RETURNS_DEEP_STUBS);
        when(context.buildConstraintViolationWithTemplate(any())).thenReturn(mock(ConstraintValidatorContext.ConstraintViolationBuilder.class));
        assertFalse(validator.isValid(vanillaOption, context));
    }

    @Test
    @Parameters({
            "2018-06-12, 2018-06-12, 2018-06-13",
            "2018-06-02, 2018-06-10, 2018-06-13",
    })
    public void success(String expiryDate, String premiumDate, String deliveryDate) {
        VanillaOption vanillaOption = new VanillaOption();
        vanillaOption.setType(TradeType.VANILLA_OPTION);
        vanillaOption.setPremiumDate(premiumDate);
        vanillaOption.setExpiryDate(expiryDate);
        vanillaOption.setDeliveryDate(deliveryDate);
        ConstraintValidatorContext context = mock(ConstraintValidatorContext.class, RETURNS_DEEP_STUBS);
        when(context.buildConstraintViolationWithTemplate(any())).thenReturn(mock(ConstraintValidatorContext.ConstraintViolationBuilder.class));
        assertTrue(validator.isValid(vanillaOption, context));
    }
}