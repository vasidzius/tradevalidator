package com.vasidzius.tradevalidator.validation.rules.general.customer;

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
public class SupportCounterPartyValidatorTest {

    private SupportCounterPartyValidator validator;

    @Before
    public void init(){
        validator = new SupportCounterPartyValidator();
        validator.initialize(Mockito.mock(SupportCounterParty.class));
    }

    @Test
    @Parameters({
            "PLUTO1",
            "PLUTO2"
    })
    public void succes(String customer){
        assertTrue(validator.isValid(customer, Mockito.mock(ConstraintValidatorContext.class)));
    }

    @Test
    @Parameters({
            "PLUTO3",
            "PLUTO4"
    })
    public void failed(String customer){
        ConstraintValidatorContext context = Mockito.mock(ConstraintValidatorContext.class, Mockito.RETURNS_DEEP_STUBS);
        when(context.buildConstraintViolationWithTemplate(any())).thenReturn(mock(ConstraintValidatorContext.ConstraintViolationBuilder.class));
        assertFalse(validator.isValid(customer, context));
    }




}