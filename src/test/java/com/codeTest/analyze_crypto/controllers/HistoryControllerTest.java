package com.codeTest.analyze_crypto.controllers;

import com.codeTest.analyze_crypto.dtos.history.HistoryGetDto;
import com.codeTest.analyze_crypto.dtos.history.HistoryPostDto;
import com.codeTest.analyze_crypto.services.HistoryService;
import com.codeTest.analyze_crypto.utils.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(HistoryController.class)
@Import(HistoryController.class)
@ContextConfiguration(classes = {Utils.class})
public class HistoryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HistoryService historyService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Utils utilities;

    @Test
    public void ShouldReturnAllHistoryGetDtosWhenGetAllHistories() throws Exception {
        HistoryGetDto historyGetDto = utilities.buildHistoryGetDto(1L,
                Utils.TEST_CURRENCY,
                Utils.TEST_DATE,
                Utils.TEST_OPEN,
                Utils.TEST_HIGH,
                Utils.TEST_LOW,
                Utils.TEST_CLOSE,
                Utils.TEST_VOLUME,
                Utils.TEST_MARKET_CAP);
        BDDMockito.given(historyService.getAllHistoies()).willReturn(List.of(historyGetDto));
        this.mockMvc.perform(get("/histories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0]").exists())
                .andExpect(jsonPath("$.[0].id").value(1L))
                .andExpect(jsonPath("$.[0].currency").value(Utils.TEST_CURRENCY.toString()))
                .andExpect(jsonPath("$.[0].date").value(Utils.TEST_DATE.toString()))
                .andExpect(jsonPath("$.[0].open").value(Utils.TEST_OPEN))
                .andExpect(jsonPath("$.[0].high").value(Utils.TEST_HIGH))
                .andExpect(jsonPath("$.[0].low").value(Utils.TEST_LOW))
                .andExpect(jsonPath("$.[0].close").value(Utils.TEST_CLOSE))
                .andExpect(jsonPath("$.[0].volume").value(Utils.TEST_VOLUME))
                .andExpect(jsonPath("$.[0].market_cap").value(Utils.TEST_MARKET_CAP));
    }

    @Test
    public void ShouldReturnHistoryGetDtoWhenAdd() throws Exception {
        HistoryGetDto historyGetDto = utilities.buildHistoryGetDto(1L,
                Utils.TEST_CURRENCY,
                Utils.TEST_DATE,
                Utils.TEST_OPEN,
                Utils.TEST_HIGH,
                Utils.TEST_LOW,
                Utils.TEST_CLOSE,
                Utils.TEST_VOLUME,
                Utils.TEST_MARKET_CAP);
        HistoryPostDto historyPostDto = utilities.buildHistoryPostDto(
                Utils.TEST_CURRENCY,
                Utils.TEST_DATE,
                Utils.TEST_OPEN,
                Utils.TEST_HIGH,
                Utils.TEST_LOW,
                Utils.TEST_CLOSE,
                Utils.TEST_VOLUME,
                Utils.TEST_MARKET_CAP);
        BDDMockito.given(historyService.create(historyPostDto)).willReturn(historyGetDto);

        this.mockMvc.perform(post("/histories/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(historyPostDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.currency").value(Utils.TEST_CURRENCY.toString()))
                .andExpect(jsonPath("$.date").value(Utils.TEST_DATE.toString()))
                .andExpect(jsonPath("$.open").value(Utils.TEST_OPEN))
                .andExpect(jsonPath("$.high").value(Utils.TEST_HIGH))
                .andExpect(jsonPath("$.low").value(Utils.TEST_LOW))
                .andExpect(jsonPath("$.close").value(Utils.TEST_CLOSE))
                .andExpect(jsonPath("$.volume").value(Utils.TEST_VOLUME))
                .andExpect(jsonPath("$.market_cap").value(Utils.TEST_MARKET_CAP));
    }

    @Test
    public void ShouldReturnLatestHistoryGetdtoWhenLatest() throws Exception {
        HistoryGetDto latestHistoryGetDto = utilities.buildHistoryGetDto(1L,
                Utils.TEST_CURRENCY,
                Utils.TEST_LATESTDATE,
                Utils.TEST_OPEN,
                Utils.TEST_HIGH,
                Utils.TEST_LOW,
                Utils.TEST_CLOSE,
                Utils.TEST_VOLUME,
                Utils.TEST_MARKET_CAP);

        BDDMockito.given(historyService.findLatestHistories()).willReturn(List.of(latestHistoryGetDto));
        this.mockMvc.perform(get("/histories/latest"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0]").exists())
                .andExpect(jsonPath("$.[0].date").value(Utils.TEST_LATESTDATE.toString()));
    }

    @Test
    public void ShouldReturnStringListWhenCurrencies() throws Exception {
        List<String> currencies = new ArrayList<>();
        currencies.add(Utils.TEST_CURRENCY);
        currencies.add(Utils.TEST_CURRENCY_New);

        BDDMockito.given(historyService.findAllCurrencyType()).willReturn(currencies);
        this.mockMvc.perform(get("/histories/currencies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0]").exists())
                .andExpect(jsonPath("$.[0]").value(Utils.TEST_CURRENCY.toString()))
                .andExpect(jsonPath("$.[1]").exists())
                .andExpect(jsonPath("$.[1]").value(Utils.TEST_CURRENCY_New.toString()));
    }
}
