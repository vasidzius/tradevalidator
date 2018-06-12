package com.vasidzius.tradevalidator.validation.rules.general.valuedate;

import com.vasidzius.tradevalidator.model.TradeInfo;
import com.vasidzius.tradevalidator.validation.rules.general.currency.CurrencyValidator;
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
public class IsValueDateCurrencyHolidaysValidatorTest {

    private IsValueDateCurrencyHolidaysValidator currencyHolidaysValidator;


    @Before
    public void init(){
        CurrencyValidator currencyValidator = new CurrencyValidator();
        currencyHolidaysValidator = new IsValueDateCurrencyHolidaysValidator(currencyValidator);
        currencyHolidaysValidator.initialize(Mockito.mock(IsValueDateCurrencyHolidays.class));
    }

    @Test
    @Parameters({
            "2016-01-01",
            "2016-03-25",
            "2016-01-18",
            "2016-02-15"
    })
    public void failed(String valueDate){
        TradeInfo tradeInfo = new TradeInfo();
        tradeInfo.setCcyPair("EURUSD");
        tradeInfo.setValueDate(valueDate);
        ConstraintValidatorContext context = Mockito.mock(ConstraintValidatorContext.class, Mockito.RETURNS_DEEP_STUBS);
        when(context.buildConstraintViolationWithTemplate(any())).thenReturn(mock(ConstraintValidatorContext.ConstraintViolationBuilder.class));
        assertFalse(currencyHolidaysValidator.isValid(tradeInfo, context));
    }

    @Test
    @Parameters({
            "2016-01-02",
            "2016-03-26",
            "2016-01-19",
            "2016-04-15"
    })
    public void success(String valueDate){
        TradeInfo tradeInfo = new TradeInfo();
        tradeInfo.setCcyPair("EURUSD");
        tradeInfo.setValueDate(valueDate);
        assertTrue(currencyHolidaysValidator.isValid(tradeInfo, Mockito.mock(ConstraintValidatorContext.class)));
    }



}