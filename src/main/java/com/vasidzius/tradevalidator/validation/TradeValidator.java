package com.vasidzius.tradevalidator.validation;

import com.vasidzius.tradevalidator.model.TradeInfo;
import com.vasidzius.tradevalidator.model.products.spotforward.SpotForward;
import com.vasidzius.tradevalidator.model.products.vanillaoption.VanillaOption;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.vasidzius.tradevalidator.model.TradeType.*;

/**
 * The type Trade validator encapsulate validation logic
 */
@Component
@AllArgsConstructor
public class TradeValidator {

    private Validator validator;

    /**
     * Main entry point for validation
     *
     * @param tradeInfo the trade info
     * @return the list
     */
    public List<Error> validate(TradeInfo tradeInfo) {

        List<Error> tradeInfoErrors = generalValidation(tradeInfo);
        tradeInfoErrors.addAll(spotForwardValidation(tradeInfo));
        tradeInfoErrors.addAll(vanillaOptionValidation(tradeInfo));

        return tradeInfoErrors;
    }

    private List<Error> generalValidation(TradeInfo tradeInfo) {
        Set<ConstraintViolation<TradeInfo>> constraintViolationsTradeInfo = validator.validate(tradeInfo);
        return constraintViolationsTradeInfo.stream()
                .map(violation -> new Error(tradeInfo, violation.getMessage()))
                .collect(Collectors.toList());
    }

    private List<Error> spotForwardValidation(TradeInfo tradeInfo) {
        if (SPOT == (tradeInfo.getType()) || FORWARD == (tradeInfo.getType())) {
            SpotForward spotForward = convertToSpotForward(tradeInfo);
            Set<ConstraintViolation<SpotForward>> constraintViolationsSpotForward = validator.validate(spotForward);
            return constraintViolationsSpotForward.stream()
                    .map(violation -> new Error(tradeInfo, violation.getMessage()))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    private List<Error> vanillaOptionValidation(TradeInfo tradeInfo) {
        if (VANILLA_OPTION == (tradeInfo.getType())) {
            VanillaOption vanillaOption = convertToVanillaOption(tradeInfo);
            Set<ConstraintViolation<VanillaOption>> constraintViolationsOption = validator.validate(vanillaOption);
            return constraintViolationsOption.stream()
                    .map(violation -> new Error(tradeInfo, violation.getMessage()))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    private SpotForward convertToSpotForward(TradeInfo tradeInfo) {
        return new SpotForward(
                tradeInfo.getCustomer(),
                tradeInfo.getCcyPair(),
                tradeInfo.getType(),
                tradeInfo.getDirection(),
                tradeInfo.getTradeDate(),
                tradeInfo.getAmount1(),
                tradeInfo.getAmount2(),
                tradeInfo.getRate(),
                tradeInfo.getValueDate(),
                tradeInfo.getLegalEntity(),
                tradeInfo.getTrader()
        );
    }

    private VanillaOption convertToVanillaOption(TradeInfo tradeInfo) {
        return new VanillaOption(
                tradeInfo.getCustomer(),
                tradeInfo.getCcyPair(),
                tradeInfo.getType(),
                tradeInfo.getStyle(),
                tradeInfo.getDirection(),
                tradeInfo.getStrategy(),
                tradeInfo.getTradeDate(),
                tradeInfo.getValueDate(),
                tradeInfo.getAmount1(),
                tradeInfo.getAmount2(),
                tradeInfo.getRate(),
                tradeInfo.getDeliveryDate(),
                tradeInfo.getExpiryDate(),
                tradeInfo.getExerciseStartDate(),
                tradeInfo.getPayCcy(),
                tradeInfo.getPremium(),
                tradeInfo.getPremiumCcy(),
                tradeInfo.getPremiumType(),
                tradeInfo.getPremiumDate(),
                tradeInfo.getLegalEntity(),
                tradeInfo.getTrader()
        );
    }
}
