package com.vasidzius.tradevalidator.model;


import com.vasidzius.tradevalidator.model.products.vanillaoption.StyleType;
import com.vasidzius.tradevalidator.validation.rules.general.GeneralValidation;
import com.vasidzius.tradevalidator.validation.rules.general.currency.ValidCurrency;
import com.vasidzius.tradevalidator.validation.rules.general.customer.SupportCounterParty;
import com.vasidzius.tradevalidator.validation.rules.general.tradetype.ValidTradeType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * The type Trade info.
 */
@Data
@EqualsAndHashCode(of = "id")
@GeneralValidation
@ApiModel
public class TradeInfo {

    @ApiModelProperty(hidden = true)
    private transient final long id;

    @NotNull(message = "customer must not be null")
    @Size(min = 1)
    @SupportCounterParty
    private String customer;

    @ValidCurrency
    @Size(min = 6, max = 6, message = "Currency Pair (ccyPair) must be string of 6 symbols, three for each currency")
    private String ccyPair;

    @ValidTradeType
    private TradeType type;

    private StyleType style;
    private String direction;
    private String strategy;

    @NotNull(message = "tradeDate must not be null")
    private String tradeDate;

    private LocalDate currentDate;
    private String valueDate;
    private String expiryDate;
    private String deliveryDate;
    private String exerciseStartDate;

    private Double amount1;
    private Double amount2;
    private Double rate;
    private String payCcy;
    private Double premium;
    private String premiumCcy;
    private String premiumType;
    private String premiumDate;

    @Pattern(regexp = "^(CS Zurich)$", message = "Only legalEntity is CS Zurich")
    private String legalEntity;

    private String trader;

    /**
     * Instantiates a new Trade info.
     */
    public TradeInfo() {
        id = IdValueGenerator.generateValue();
        currentDate = LocalDate.parse("2017-07-18");
    }
}
