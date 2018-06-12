package com.vasidzius.tradevalidator.validation.rules.general.valuedate;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vasidzius.tradevalidator.GsonUtils;
import com.vasidzius.tradevalidator.model.TradeInfo;
import com.vasidzius.tradevalidator.validation.rules.general.currency.CurrencyValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.util.*;

/**
 * The type Is valueDate field of TradeInfo currency holidays.
 */
@Component
@AllArgsConstructor
public class IsValueDateCurrencyHolidaysValidator implements ConstraintValidator<IsValueDateCurrencyHolidays, TradeInfo> {

    private final CurrencyValidator currencyValidator;

    private final List<PublicHoliday> publicHolidays = loadCurrencyHolidays(
            "publicHolidaysEUR.json",
            "publicHolidaysUSD.json");

    @Override
    public boolean isValid(TradeInfo tradeInfo, ConstraintValidatorContext context) {
        String valueDate = tradeInfo.getValueDate();
        if (valueDate != null) {
            String first = currencyValidator.getFirstCurrency(tradeInfo.getCcyPair());
            String second = currencyValidator.getSecondCurrency(tradeInfo.getCcyPair());
            boolean validateFirst = validate(context, valueDate, first);
            boolean validateSecond = validate(context, valueDate, second);
            return validateFirst && validateSecond;
        }
        return true;
    }

    private boolean validate(ConstraintValidatorContext context, String valueDate, String first) {
        if (currencyValidator.isValidCurrency(first, context)) {
            Optional<PublicHoliday> optionalHoliday = publicHolidays.stream().filter(publicHoliday ->
                    first.equals(publicHoliday.getCurrency()) &&
                            LocalDate.parse(valueDate).equals(publicHoliday.getDate())).findFirst();
            if(optionalHoliday.isPresent()){
                context.disableDefaultConstraintViolation();
                String currency = optionalHoliday.get().getCurrency();
                String publicHoliday = optionalHoliday.get().getPublicHoliday();
                context.buildConstraintViolationWithTemplate(String.format(
                        "For %s date %s is Public Holiday - %s", currency, valueDate, publicHoliday))
                        .addConstraintViolation();
                return false;
            }
        }
        return true;
    }

    private List<PublicHoliday> loadCurrencyHolidays(String... args) {
        List<PublicHoliday> resultList = new ArrayList<>();
        Arrays.stream(args).forEach(jsonFile -> {
            Gson gson = new Gson();
            String jsonAsString = GsonUtils.getJsonAsString(jsonFile);
            List<PublicHoliday> publicHolidaysEUR = gson.fromJson(jsonAsString, new TypeToken<List<PublicHoliday>>() {
            }.getType());

            resultList.addAll(publicHolidaysEUR);
        });

        return Collections.unmodifiableList(resultList);
    }
}
