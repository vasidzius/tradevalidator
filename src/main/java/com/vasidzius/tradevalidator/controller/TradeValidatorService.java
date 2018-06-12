package com.vasidzius.tradevalidator.controller;

import com.vasidzius.tradevalidator.model.ErrorResponse;
import com.vasidzius.tradevalidator.model.TradeInfo;
import com.vasidzius.tradevalidator.validation.TradeValidator;
import lombok.AllArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/validator")
@AllArgsConstructor
public class TradeValidatorService {

    private TradeValidator tradeValidator;

    @PostMapping
    public ResponseEntity validate(@RequestBody List<TradeInfo> tradeInfos){
        List<ErrorResponse> errorResponses = new ArrayList<>();
        tradeInfos.forEach(tradeInfo -> {

            Optional<ErrorResponse> validateValueDate = tradeValidator.validateValueDate(tradeInfo);
            validateValueDate.ifPresent(errorResponses::add);

            Optional<ErrorResponse> isWeekend = tradeValidator.checkWeekend(tradeInfo);
            isWeekend.ifPresent(errorResponses::add);

            Optional<ErrorResponse> validateCurrency = tradeValidator.validateCurrency(tradeInfo);
            isWeekend.ifPresent(errorResponses::add);



        });
        if(errorResponses.isEmpty()) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponses);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleException(MethodArgumentNotValidException exception) {

        String errorMsg = exception.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .findFirst()
                .orElse(exception.getMessage());

        return ErrorResponse.builder().message(errorMsg).build();
    }

    @PostMapping("/validateOne")
    public ResponseEntity validateOne( @Valid @RequestBody TradeInfo tradeInfo){
        System.out.println();
        return ResponseEntity.ok().build();
    }
}
