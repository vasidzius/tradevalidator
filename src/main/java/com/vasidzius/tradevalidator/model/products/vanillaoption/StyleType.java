package com.vasidzius.tradevalidator.model.products.vanillaoption;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The enum Style type for Style field of VanillaOption product.
 */
public enum StyleType {

    AMERICAN,
    EUROPEAN,
    WRONG_TYPE;

    private static Map<String, StyleType> FORMAT_MAP = Stream
            .of(StyleType.values())
            .collect(Collectors.toMap(Enum::toString, Function.identity()));

    @JsonCreator
    public static StyleType fromString(String string) {
        return Optional
                .ofNullable(FORMAT_MAP.get(string))
                .orElse(WRONG_TYPE);
    }


}
