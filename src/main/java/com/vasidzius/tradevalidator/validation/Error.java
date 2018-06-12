package com.vasidzius.tradevalidator.validation;

import com.vasidzius.tradevalidator.model.TradeInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Error {
    private TradeInfo tradeInfo;
    private String message;
}
