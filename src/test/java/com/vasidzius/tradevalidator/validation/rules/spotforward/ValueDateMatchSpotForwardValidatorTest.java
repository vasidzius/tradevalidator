package com.vasidzius.tradevalidator.validation.rules.spotforward;

import com.vasidzius.tradevalidator.model.TradeType;
import com.vasidzius.tradevalidator.model.products.spotforward.SpotForward;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.validation.ConstraintValidatorContext;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(JUnitParamsRunner.class)
public class ValueDateMatchSpotForwardValidatorTest {

    private ValueDateMatchSpotForwardValidator validator;

    @Before
    public void init() {
        validator = new ValueDateMatchSpotForwardValidator();
        validator.initialize(mock(ValueDateMatchSpotForward.class));
    }

    @Test
    @Parameters({
            "2018-06-13, 2018-06-15, SPOT",
            "2018-06-13, 2018-06-16, FORWARD",
            "2018-06-30, 2018-07-03, FORWARD",
            "2018-06-30, 2018-07-02, SPOT",
            "2018-12-31, 2019-01-02, SPOT",
    })
    public void success(String tradeDate, String valueDate, TradeType type) {
        SpotForward spotForward = new SpotForward();
        spotForward.setTradeDate(tradeDate);
        spotForward.setValueDate(valueDate);
        spotForward.setType(type);
        ConstraintValidatorContext context = mock(ConstraintValidatorContext.class, RETURNS_DEEP_STUBS);
        when(context.buildConstraintViolationWithTemplate(any())).thenReturn(mock(ConstraintValidatorContext.ConstraintViolationBuilder.class));
        assertTrue(validator.isValid(spotForward, context));
    }

    @Test
    @Parameters({
            "2018-06-13, 2018-06-16, SPOT",
            "2018-06-13, 2018-06-15, FORWARD",
            "2018-06-30, 2018-07-01, FORWARD",
            "2018-06-30, 2018-07-03, SPOT",
            "2018-12-31, 2019-01-01, SPOT",
    })
    public void failed(String tradeDate, String valueDate, TradeType type) {
        SpotForward spotForward = new SpotForward();
        spotForward.setTradeDate(tradeDate);
        spotForward.setValueDate(valueDate);
        spotForward.setType(type);
        ConstraintValidatorContext context = mock(ConstraintValidatorContext.class, RETURNS_DEEP_STUBS);
        when(context.buildConstraintViolationWithTemplate(any())).thenReturn(mock(ConstraintValidatorContext.ConstraintViolationBuilder.class));
        assertFalse(validator.isValid(spotForward, context));
    }

}