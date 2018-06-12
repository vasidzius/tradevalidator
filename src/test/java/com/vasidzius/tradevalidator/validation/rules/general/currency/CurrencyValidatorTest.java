package com.vasidzius.tradevalidator.validation.rules.general.currency;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import javax.validation.ConstraintValidatorContext;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(JUnitParamsRunner.class)
public class CurrencyValidatorTest {

    private CurrencyValidator validator;

    @Before
    public void init(){
        validator = new CurrencyValidator();
    }

    @Test
    @Parameters({
            "EURUSD",
            "DZDAOA",
            "XCDARS"
    })
    public void success(String ccyPair){
        ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);
        assertTrue(validator.isValid(ccyPair, context));
    }

    @Test
    @Parameters({
            "EVRUSD",
            "ASDFGH",
            "AXCDAE"
    })
    public void failed(String ccyPair){
        ConstraintValidatorContext context = mock(ConstraintValidatorContext.class, Mockito.RETURNS_DEEP_STUBS);
        when(context.buildConstraintViolationWithTemplate(any())).thenReturn(mock(ConstraintValidatorContext.ConstraintViolationBuilder.class));
        assertFalse(validator.isValid(ccyPair, context));
    }
}