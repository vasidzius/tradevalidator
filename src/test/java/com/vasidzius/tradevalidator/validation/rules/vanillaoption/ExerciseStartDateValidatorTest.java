package com.vasidzius.tradevalidator.validation.rules.vanillaoption;

import com.vasidzius.tradevalidator.model.TradeType;
import com.vasidzius.tradevalidator.model.products.vanillaoption.VanillaOption;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.validation.ConstraintValidatorContext;

import static com.vasidzius.tradevalidator.model.products.vanillaoption.StyleType.AMERICAN;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(JUnitParamsRunner.class)
public class ExerciseStartDateValidatorTest {

    private ExerciseStartDateValidator validator;

    @Before
    public void init() {
        validator = new ExerciseStartDateValidator();
        validator.initialize(mock(ExerciseStartDate.class));
    }

    @Test
    public void exersiceStartDateIsMissed() {
        VanillaOption vanillaOption = new VanillaOption();
        vanillaOption.setType(TradeType.VANILLA_OPTION);
        vanillaOption.setStyle(AMERICAN);
        ConstraintValidatorContext context = mock(ConstraintValidatorContext.class, RETURNS_DEEP_STUBS);
        when(context.buildConstraintViolationWithTemplate(any())).thenReturn(mock(ConstraintValidatorContext.ConstraintViolationBuilder.class));
        assertFalse(validator.isValid(vanillaOption, context));
    }

    @Test
    @Parameters({
            "2018-06-13, 2018-06-13, 2018-06-13",
            "2018-06-13, 2018-06-11, 2018-06-15",
            "2018-06-13, 2018-06-15, 2018-06-15",
            "2018-06-13, 2018-06-17, 2018-06-15",
    })
    public void failed(String tradeDate, String exerciseStartDate, String expiryDate) {
        VanillaOption vanillaOption = new VanillaOption();
        vanillaOption.setType(TradeType.VANILLA_OPTION);
        vanillaOption.setStyle(AMERICAN);
        vanillaOption.setExerciseStartDate(exerciseStartDate);
        vanillaOption.setTradeDate(tradeDate);
        vanillaOption.setExpiryDate(expiryDate);
        ConstraintValidatorContext context = mock(ConstraintValidatorContext.class, RETURNS_DEEP_STUBS);
        when(context.buildConstraintViolationWithTemplate(any())).thenReturn(mock(ConstraintValidatorContext.ConstraintViolationBuilder.class));
        assertFalse(validator.isValid(vanillaOption, context));
    }

    @Test
    @Parameters({
            "2018-06-13, 2018-06-14, 2018-06-15",
            "2018-06-10, 2018-06-11, 2018-06-20",
            "2018-06-30, 2018-07-15, 2018-07-20",
    })
    public void success(String tradeDate, String exerciseStartDate, String expiryDate) {
        VanillaOption vanillaOption = new VanillaOption();
        vanillaOption.setType(TradeType.VANILLA_OPTION);
        vanillaOption.setStyle(AMERICAN);
        vanillaOption.setExerciseStartDate(exerciseStartDate);
        vanillaOption.setTradeDate(tradeDate);
        vanillaOption.setExpiryDate(expiryDate);
        ConstraintValidatorContext context = mock(ConstraintValidatorContext.class, RETURNS_DEEP_STUBS);
        when(context.buildConstraintViolationWithTemplate(any())).thenReturn(mock(ConstraintValidatorContext.ConstraintViolationBuilder.class));
        assertTrue(validator.isValid(vanillaOption, context));
    }

}