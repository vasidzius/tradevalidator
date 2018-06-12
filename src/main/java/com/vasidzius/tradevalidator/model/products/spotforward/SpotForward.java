package com.vasidzius.tradevalidator.model.products.spotforward;

import com.vasidzius.tradevalidator.model.TradeType;
import com.vasidzius.tradevalidator.validation.rules.spotforward.ValueDateMatchSpotForward;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * The type describes Spot and Forward trade products.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

@ValueDateMatchSpotForward
public class SpotForward {

    private String customer;
    private String ccyPair;
    private TradeType type;
    private String direction;
    private String tradeDate;
    private Double amount1;
    private Double amount2;
    private Double rate;
    @NotNull(message = "valueDate must not be null")
    private String valueDate;
    private String legalEntity;
    private String trader;
}
