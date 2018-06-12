package com.vasidzius.tradevalidator.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class TradeInfoTest {

    @Test
    public void test(){

        TradeInfo tradeInfo = new TradeInfo("a");
        System.out.printf(tradeInfo.toString());
    }

}