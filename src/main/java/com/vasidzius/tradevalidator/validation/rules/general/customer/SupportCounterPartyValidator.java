package com.vasidzius.tradevalidator.validation.rules.general.customer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vasidzius.tradevalidator.GsonUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collections;
import java.util.List;

/**
 * The type Support counter party validator validates that
 * counter party (customer field of TradeInfo) is valid one
 */
public class SupportCounterPartyValidator implements ConstraintValidator<SupportCounterParty, String> {

    private final List<String> supportedCustomers = loadSupprotedCustomers();

    @Override
    public boolean isValid(String customer, ConstraintValidatorContext context) {
        if (!supportedCustomers.contains(customer)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(String.format("Counter party (customer) must be one of the supported ones : %s", supportedCustomers))
                    .addConstraintViolation();
            return false;
        }
        return true;
    }

    private List<String> loadSupprotedCustomers() {
        Gson gson = new Gson();
        String jsonAsString = GsonUtils.getJsonAsString("supportedCounterParties.json");
        List<String> customers = gson.fromJson(jsonAsString, new TypeToken<List<String>>() {
        }.getType());
        return Collections.unmodifiableList(customers);
    }
}
