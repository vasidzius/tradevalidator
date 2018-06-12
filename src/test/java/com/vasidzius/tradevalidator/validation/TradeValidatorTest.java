package com.vasidzius.tradevalidator.validation;

import com.vasidzius.tradevalidator.model.TradeInfo;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

@RunWith(JUnitParamsRunner.class)
public class TradeValidatorTest {

    private TradeValidator tradeValidator = new TradeValidator();

    @Test
    @Parameters({"2018-06-09","2018-06-10"})
    public void weekendTrue(String valueDate) {
        //given
        TradeInfo tradeInfo = new TradeInfo();
        tradeInfo.setValueDate(valueDate);

        //when //then
        assertTrue(tradeValidator.checkWeekend(tradeInfo).isPresent());
    }

    @Test
    @Parameters({"2018-06-11","2018-06-12"})
    public void weekendFalse(String valueDate) {
        //given
        TradeInfo tradeInfo = new TradeInfo();
        tradeInfo.setValueDate(valueDate);

        //when //then
        assertFalse(tradeValidator.checkWeekend(tradeInfo).isPresent());
    }

//    @Test
//    @Parameters({"EURUSD", "USDEUR"})
//    public void

}