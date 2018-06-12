package com.vasidzius.tradevalidator.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The enum Trade type describes possible trade products
 */
public enum TradeType {

    SPOT("Spot"),
    FORWARD("Forward"),
    VANILLA_OPTION("VanillaOption"),
    WRONG_TYPE("WRONG_TYPE");

    private static Map<String, TradeType> FORMAT_MAP = Stream
            .of(TradeType.values())
            .collect(Collectors.toMap(s -> s.value, Function.identity()));
    @Getter
    @JsonValue()
    private String value;

    TradeType(String value) {
        this.value = value;
    }

    @JsonCreator
    public static TradeType fromString(String string) {
        return Optional
                .ofNullable(FORMAT_MAP.get(string))
                .orElse(WRONG_TYPE);
    }
}
