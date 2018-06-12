package com.vasidzius.tradevalidator.validation.rules.general.valuedate;

import com.vasidzius.tradevalidator.model.TradeInfo;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import javax.validation.ConstraintValidatorContext;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

@RunWith(JUnitParamsRunner.class)
public class IsValueDateWeekendValidatorTest {

    private IsValueDateWeekendValidator validator = new IsValueDateWeekendValidator();

    @Test
    @Parameters({"2018-06-11","2018-06-12"})
    public void weekendTrue(String valueDate) {
        //given
        TradeInfo tradeInfo = new TradeInfo();
        tradeInfo.setValueDate(valueDate);

        //when //then
        assertTrue(validator.isValid(tradeInfo, Mockito.mock(ConstraintValidatorContext.class)));
    }

    @Test
    @Parameters({"2018-06-09","2018-06-10"})
    public void weekendFalse(String valueDate) {
        //given
        TradeInfo tradeInfo = new TradeInfo();
        tradeInfo.setValueDate(valueDate);

        //when //then
        assertFalse(validator.isValid(tradeInfo, Mockito.mock(ConstraintValidatorContext.class)));
    }

}