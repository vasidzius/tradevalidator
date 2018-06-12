package com.vasidzius.tradevalidator.model.products.vanillaoption;

import com.vasidzius.tradevalidator.model.TradeType;
import com.vasidzius.tradevalidator.validation.rules.general.currency.ValidCurrency;
import com.vasidzius.tradevalidator.validation.rules.vanillaoption.ExerciseStartDate;
import com.vasidzius.tradevalidator.validation.rules.vanillaoption.ExpiryDatePremiumDateBeforeDeliveryDate;
import com.vasidzius.tradevalidator.validation.rules.vanillaoption.VanillaOptionStyle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * The type Vanilla Option Trade product.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

@ExerciseStartDate
@ExpiryDatePremiumDateBeforeDeliveryDate
public class VanillaOption {

    private String customer;

    @ValidCurrency
    private String ccyPair;
    private TradeType type;

    @VanillaOptionStyle
    private StyleType style;

    private String direction;
    private String strategy;
    private String tradeDate;
    private String valueDate;
    private Double amount1;
    private Double amount2;
    private Double rate;
    @NotNull(message = "deliveryDate must not be null")
    private String deliveryDate;
    @NotNull(message = "expiryDate must not be null")
    private String expiryDate;
    private String exerciseStartDate;
    private String payCcy;
    private Double premium;
    private String premiumCcy;
    private String premiumType;
    @NotNull(message = "premiumDate must not be null")
    private String premiumDate;
    private String legalEntity;
    private String trader;
}
