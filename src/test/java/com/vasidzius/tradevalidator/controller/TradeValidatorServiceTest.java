package com.vasidzius.tradevalidator.controller;

import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vasidzius.tradevalidator.model.ErrorResponse;
import com.vasidzius.tradevalidator.model.TradeInfo;
import com.vasidzius.tradevalidator.validation.TradeValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static junit.framework.TestCase.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TradeValidatorServiceTest {

    @Autowired
    private TradeValidatorService tradeValidatorService;

    @Autowired
    private TradeValidator tradeValidator;

    private MockMvc mockMvc;

    private Gson gson = new Gson();

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(tradeValidatorService).build();
    }

    @Test
    public void valueDateBeforeTradeDate() throws Exception {
        //given
        String dataJson = getJsonAsString("valueDateBeforeTradeDate.json");

        //when //then
        MvcResult mvcResult = mockMvc.perform(
                post("/validator")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dataJson))
                .andExpect(status().isBadRequest())
                .andReturn();

        //then
        String contentAsString = mvcResult.getResponse().getContentAsString();
        List<ErrorResponse> errors = gson.fromJson(contentAsString, new TypeToken<List<ErrorResponse>>() {}.getType());
        assertTrue(errors.contains(new ErrorResponse("valueDate is before tradeDate")));
    }

    @Test
    public void valueDateAfterTradeDate() throws Exception {
        //given
        String dataJson = getJsonAsString("valueDateAfterTradeDate.json");

        //when //then
        MvcResult mvcResult = mockMvc.perform(
                post("/validator")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dataJson))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void valueDateisMissedForOption() throws Exception {
        //given
        String dataJson = getJsonAsString("valueDateisMissedForOption.json");

        //when //then
        MvcResult mvcResult = mockMvc.perform(
                post("/validator")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dataJson))
                .andExpect(status().isOk())
                .andReturn();
    }

    private String getJsonAsString(String s) throws IOException {
        File file = new File(getClass().getResource(s).getFile());
        return Files.asCharSource(file, Charset.defaultCharset()).read();
    }


//    @Test
//    public void test() {
//
//        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//        Validator validator = factory.getValidator();
//
//        TradeValidatorService tradeValidatorService = new TradeValidatorService(tradeValidator);
//
//        TradeInfo tradeInfo = new TradeInfo("a");
//        tradeInfo.setCcyPair("ARO");
//
//        Set<ConstraintViolation<TradeInfo>> validate = validator.validate(tradeInfo);
//
//        System.out.println(validate);
//
//    }
//
//    @Test
//    public void test2() throws Exception {
//        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(tradeValidatorService).build();
//
//        String dataJson = getJsonAsString("data.json");
//        ResultActions perform = mockMvc.perform(post("/validator").contentType(MediaType.APPLICATION_JSON).content(dataJson));
//        MvcResult mvcResult = perform.andReturn();
//    }
//
//    @Test
//    public void validateOneSuccess() throws Exception {
//        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(tradeValidatorService).build();
//
//        String dataJson = getJsonAsString("dataOneSuccess.json");
//        ResultActions perform = mockMvc.perform(post("/validator/validateOne").contentType(MediaType.APPLICATION_JSON).content(dataJson));
//        MvcResult mvcResult = perform.andReturn();
//        System.out.println();
//    }
//
//    @Test
//    public void validateOneFailed() throws Exception {
//        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(tradeValidatorService).build();
//
//        String dataJson = getJsonAsString("dataOneFailed.json");
//        ResultActions perform = mockMvc.perform(post("/validator/validateOne").contentType(MediaType.APPLICATION_JSON).content(dataJson));
//        MvcResult mvcResult = perform.andReturn();
//        System.out.println();
//    }

}