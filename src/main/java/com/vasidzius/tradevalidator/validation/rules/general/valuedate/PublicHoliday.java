package com.vasidzius.tradevalidator.validation.rules.general.valuedate;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * The type Public holiday. It describes Date, Holiday name for given currency that
 * is match to ISO4217
 */
@Data
@NoArgsConstructor
public class PublicHoliday {

    private LocalDate date;
    private String currency;
    private String publicHoliday;

}
