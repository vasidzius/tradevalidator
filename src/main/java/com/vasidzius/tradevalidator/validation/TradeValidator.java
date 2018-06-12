package com.vasidzius.tradevalidator.validation;

import com.vasidzius.tradevalidator.model.ErrorResponse;
import com.vasidzius.tradevalidator.model.TradeInfo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validation;
import javax.validation.Validator;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Optional;

@Service
//@AllArgsConstructor
public class TradeValidator {

    public Optional<ErrorResponse> validateValueDate(TradeInfo tradeInfo) {


        if (tradeInfo.getValueDate() != null) {
            LocalDate valueDate = LocalDate.parse(tradeInfo.getValueDate());
            LocalDate tradeDate = LocalDate.parse(tradeInfo.getTradeDate());

            int compare = valueDate.compareTo(tradeDate);
            if (compare < 0) {
                return Optional.of(new ErrorResponse("valueDate is before tradeDate"));
            }
        }
        return Optional.empty();
    }

    public Optional<ErrorResponse> checkWeekend(TradeInfo tradeInfo) {
        if (tradeInfo.getValueDate() != null) {
            LocalDate valueDate = LocalDate.parse(tradeInfo.getValueDate());
            boolean isWeekend = valueDate.getDayOfWeek() == DayOfWeek.SATURDAY ||
                    valueDate.getDayOfWeek() == DayOfWeek.SUNDAY;
            if(isWeekend){
            return Optional.of(new ErrorResponse("valueDate is weekend"));
            }
        }
        return Optional.empty();
    }

    public Optional<ErrorResponse> validateCurrency(TradeInfo tradeInfo) {
        return null;
    }
}
