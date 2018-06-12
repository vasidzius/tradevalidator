package com.vasidzius.tradevalidator.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vasidzius.tradevalidator.GsonUtils;
import com.vasidzius.tradevalidator.model.TradeInfo;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Map;
import java.util.Set;

import static com.vasidzius.tradevalidator.GsonUtils.getJsonAsString;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TradeValidatorServiceTest {

    @Autowired
    private TradeValidatorService tradeValidatorService;

    private MockMvc mockMvc;

    private Gson gson = GsonUtils.getGson();

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(tradeValidatorService).build();
    }

    @Test
    public void failedValidation() throws Exception {
        //given
        String dataJson = getJsonAsString(getClass(), "failedDataOneForEachType.json");

        //when //then
        MvcResult mvcResult = mockMvc.perform(
                post("")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dataJson))
                .andExpect(status().isBadRequest())
                .andReturn();

        //then
        String contentAsString = mvcResult.getResponse().getContentAsString();
        Map<TradeInfo, Set<String>> errors = gson.fromJson(contentAsString, new TypeToken<Map<TradeInfo, Set<String>>>() {
        }.getType());

        Map.Entry<TradeInfo, Set<String>> pluto1 = errors.entrySet().stream().filter(error ->
                error.getKey().getCustomer().equals("PLUTO1"))
                .findFirst().get();
        Set<String> pluto1Errors = pluto1.getValue();
        assertTrue(pluto1Errors.contains("EVR currency doesn't match ISO 4217"));
        assertTrue(pluto1Errors.contains("valueDate is before tradeDate"));
        assertTrue(pluto1Errors.contains("Spot value date(2016-08-10) must be 2 days after trade date(2016-08-11), that is 2016-08-13"));
        assertTrue(pluto1Errors.contains("tradeDate(2016-08-11) is not after currentDate(2017-07-18)"));
        assertEquals(4, pluto1Errors.size());

        Map.Entry<TradeInfo, Set<String>> pluto2 = errors.entrySet().stream().filter(error ->
                error.getKey().getCustomer().equals("PLUTO2"))
                .findFirst().get();
        Set<String> pluto2Errors = pluto2.getValue();
        assertTrue(pluto2Errors.contains("valueDate is weekend"));
        assertTrue(pluto2Errors.contains("UST currency doesn't match ISO 4217"));
        assertTrue(pluto2Errors.contains("Wrong trade type, must be one of Spot, Forward, VanillaOption"));
        assertTrue(pluto2Errors.contains("tradeDate(2016-08-11) is not after currentDate(2017-07-18)"));
        assertEquals(4, pluto2Errors.size());

        Map.Entry<TradeInfo, Set<String>> pluto21 = errors.entrySet().stream().filter(error ->
                error.getKey().getCustomer().equals("PLUTO21"))
                .findFirst().get();
        Set<String> pluto21Errors = pluto21.getValue();
        assertTrue(pluto21Errors.contains("For USD date 2016-12-26 is Public Holiday - Christmas Day"));
        assertTrue(pluto21Errors.contains("For EUR date 2016-12-26 is Public Holiday - Christmas Day"));
        assertTrue(pluto21Errors.contains("Counter party (customer) must be one of the supported ones : [PLUTO1, PLUTO2]"));
        assertTrue(pluto21Errors.contains("Forward value date(2016-12-26) must be more than 2 days after trade date(2016-12-24), that is more than 2016-12-26"));
        assertTrue(pluto21Errors.contains("tradeDate(2016-12-24) is not after currentDate(2017-07-18)"));
        assertEquals(5, pluto21Errors.size());

        Map.Entry<TradeInfo, Set<String>> pluto3 = errors.entrySet().stream().filter(error ->
                error.getKey().getCustomer().equals("PLUTO3"))
                .findFirst().get();
        Set<String> pluto3Errors = pluto3.getValue();
        assertTrue(pluto3Errors.contains("Counter party (customer) must be one of the supported ones : [PLUTO1, PLUTO2]"));
        assertTrue(pluto3Errors.contains("Wrong style type, must be one of AMERICAN, EUROPEAN"));
        assertTrue(pluto3Errors.contains("Only legalEntity is CS Zurich"));
        assertTrue(pluto3Errors.contains("tradeDate(2016-08-11) is not after currentDate(2017-07-18)"));
        assertEquals(4, pluto3Errors.size());

        Map.Entry<TradeInfo, Set<String>> pluto4 = errors.entrySet().stream().filter(error ->
                error.getKey().getCustomer().equals("PLUTO4"))
                .findFirst().get();
        Set<String> pluto4Errors = pluto4.getValue();
        assertTrue(pluto4Errors.contains("Counter party (customer) must be one of the supported ones : [PLUTO1, PLUTO2]"));
        assertTrue(pluto4Errors.contains("Wrong style type, must be one of AMERICAN, EUROPEAN"));
        assertTrue(pluto4Errors.contains("tradeDate(2016-08-11) is not after currentDate(2017-07-18)"));
        assertEquals(3, pluto4Errors.size());

        Map.Entry<TradeInfo, Set<String>> pluto5 = errors.entrySet().stream().filter(error ->
                error.getKey().getCustomer().equals("PLUTO5"))
                .findFirst().get();
        Set<String> pluto5Errors = pluto5.getValue();
        assertTrue(pluto5Errors.contains("Counter party (customer) must be one of the supported ones : [PLUTO1, PLUTO2]"));
        assertTrue(pluto5Errors.contains("For AMERICAN style VANILLA_OPTION must have exerciseStartDate"));
        assertTrue(pluto5Errors.contains("tradeDate(2016-08-11) is not after currentDate(2017-07-18)"));
        assertEquals(3, pluto5Errors.size());

        Map.Entry<TradeInfo, Set<String>> pluto6 = errors.entrySet().stream().filter(error ->
                error.getKey().getCustomer().equals("PLUTO6"))
                .findFirst().get();
        Set<String> pluto6Errors = pluto6.getValue();
        assertTrue(pluto6Errors.contains("Counter party (customer) must be one of the supported ones : [PLUTO1, PLUTO2]"));
        assertTrue(pluto6Errors.contains("the exerciseStartDate(2016-08-15), which has to be after the trade date(2016-08-11) but before the expiry date(2016-08-14)"));
        assertTrue(pluto6Errors.contains("expiry/premium date(2016-08-22) shall be before delivery date(2016-08-22)"));
        assertTrue(pluto6Errors.contains("tradeDate(2016-08-11) is not after currentDate(2017-07-18)"));
        assertEquals(4, pluto6Errors.size());
    }

}