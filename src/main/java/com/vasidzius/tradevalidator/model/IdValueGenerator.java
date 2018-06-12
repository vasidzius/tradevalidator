package com.vasidzius.tradevalidator.model;

import java.util.concurrent.atomic.AtomicLong;

/**
 * The type Id value generator that is used for equals&hashCode in TradeInfo
 * as products can be absolutely the same from fields point of view in input
 */
class IdValueGenerator {

    private static AtomicLong atomicLong = new AtomicLong(0);

    static long generateValue() {
        return atomicLong.incrementAndGet();
    }
}
