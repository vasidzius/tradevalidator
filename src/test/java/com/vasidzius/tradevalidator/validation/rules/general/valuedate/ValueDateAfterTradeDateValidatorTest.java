package com.vasidzius.tradevalidator.validation.rules.general.valuedate;

import com.vasidzius.tradevalidator.model.TradeInfo;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import javax.validation.ConstraintValidatorContext;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnitParamsRunner.class)
public class ValueDateAfterTradeDateValidatorTest {

    private ValueDateAfterTradeDateValidator valueDateAfterTradeDateValidator = new ValueDateAfterTradeDateValidator();

    @Test
    @Parameters({
            "2018-06-12, 2018-06-10",
            "2018-06-12, 2018-06-12",
    })
    public void isValidTrue(String valueDate, String tradeDate){
        TradeInfo tradeInfo = new TradeInfo();
        tradeInfo.setValueDate(valueDate);
        tradeInfo.setTradeDate(tradeDate);
        assertTrue(valueDateAfterTradeDateValidator.isValid(tradeInfo, Mockito.mock(ConstraintValidatorContext.class)));
    }

    @Test
    public void valueDateIsNull(){
        TradeInfo tradeInfo = new TradeInfo();
        tradeInfo.setValueDate(null);
        assertTrue(valueDateAfterTradeDateValidator.isValid(tradeInfo, Mockito.mock(ConstraintValidatorContext.class)));
    }

    @Test
    @Parameters({
            "2018-06-10, 2018-06-12",
            "2018-06-11, 2018-06-12",
    })
    public void isValidFalse(String valueDate, String tradeDate){
        TradeInfo tradeInfo = new TradeInfo();
        tradeInfo.setValueDate(valueDate);
        tradeInfo.setTradeDate(tradeDate);
        assertFalse(valueDateAfterTradeDateValidator.isValid(tradeInfo, Mockito.mock(ConstraintValidatorContext.class)));
    }

}