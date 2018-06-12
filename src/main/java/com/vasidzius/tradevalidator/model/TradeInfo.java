package com.vasidzius.tradevalidator.model;


import com.vasidzius.tradevalidator.validation.ValidCurrency;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Size;

@Valid
@Data
public class TradeInfo {

//    @Size(min = 25)
    public String customer;

    @ValidCurrency
    public String ccyPair;
    public String type;
    public String style;
    public String direction;
    public String strategy;
    public String tradeDate;
    public String valueDate;
    public Double amount1;
    public Double amount2;
    public Double rate;
    public String deliveryDate;
    public String expiryDate;
    public String excerciseStartDate;
    public String payCcy;
    public Double premium;
    public String premiumCcy;
    public String premiumType;
    public String premiumDate;
    public String legalEntity;
    public String trader;

    public TradeInfo(){};

    public TradeInfo(String name){
        customer = name;
    }

}
