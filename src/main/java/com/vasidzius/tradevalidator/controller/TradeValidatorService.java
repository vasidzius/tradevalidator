package com.vasidzius.tradevalidator.controller;

import com.vasidzius.tradevalidator.GsonUtils;
import com.vasidzius.tradevalidator.model.TradeInfo;
import com.vasidzius.tradevalidator.validation.Error;
import com.vasidzius.tradevalidator.validation.TradeValidator;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * The type Trade validator service.
 */
@RestController
@RequestMapping
@AllArgsConstructor
@Api
public class TradeValidatorService {

    private TradeValidator tradeValidator;

    /**
     * Validate List<TradeInfo>.
     *
     * @param tradeInfos the trade infos
     * @return validation result as Map<TradeInfo, List<ErrorMessages>>
     */
    @ApiOperation(value = "validate trades")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Validation is successful")
    })
    @PostMapping
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity validate(@ApiParam(required = true) @RequestBody List<TradeInfo> tradeInfos) {

        Map<TradeInfo, List<String>> errorMap = getErrorMap(tradeInfos);

        if (errorMap.isEmpty()) {
            return ResponseEntity.ok(GsonUtils.getGson().toJson("Validation is successful"));
        }

        String jsonBody = GsonUtils.getBodyWithComplexKeyAsJson(errorMap);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonBody);
    }

    private Map<TradeInfo, List<String>> getErrorMap(@RequestBody List<TradeInfo> tradeInfos) {
        Map<TradeInfo, List<String>> result = tradeInfos.stream().collect(Collectors
                .toMap(
                        Function.identity(),
                        tradeInfo ->
                                tradeValidator.validate(tradeInfo)
                                        .stream()
                                        .map(Error::getMessage).collect(Collectors.toList())
                ));
        result.values().removeIf(List::isEmpty);
        return result;
    }

}
