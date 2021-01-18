package com.codeTest.analyze_crypto.services;

import com.codeTest.analyze_crypto.dtos.history.HistoryGetDto;
import com.codeTest.analyze_crypto.dtos.history.HistoryPostDto;
import com.codeTest.analyze_crypto.entities.History;
import com.codeTest.analyze_crypto.mappers.HistoryMapper;
import com.codeTest.analyze_crypto.mappers.HistoryMapperImpl;
import com.codeTest.analyze_crypto.repositories.HistoryRepository;
import com.codeTest.analyze_crypto.utils.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = { HistoryMapperImpl.class, Utils.class})
public class HistoryServicesTest {

    @Autowired
    private HistoryMapper historyMapper;

    @Mock
    private HistoryRepository historyRepository;

    @Autowired
    private Utils utilities;

    private HistoryService historyService;
    @BeforeEach
    void setUp(){
        historyService = new HistoryService(historyRepository, historyMapper);
    }

    @Test
    public void shouldReturnHistoryListGivenHistoriesExist () {
        History history1 = utilities.buildHistory(1l,
                Utils.TEST_CURRENCY,
                Utils.TEST_DATE,
                Utils.TEST_OPEN,
                Utils.TEST_HIGH,
                Utils.TEST_LOW,
                Utils.TEST_CLOSE,
                Utils.TEST_VOLUME,
                Utils.TEST_MARKET_CAP);
        History history2 = utilities.buildHistory(2l,
                Utils.TEST_CURRENCY,
                Utils.TEST_DATE,
                Utils.TEST_OPEN,
                Utils.TEST_HIGH,
                Utils.TEST_LOW,
                Utils.TEST_CLOSE,
                Utils.TEST_VOLUME,
                Utils.TEST_MARKET_CAP);

        when(historyRepository.findAll()).thenReturn(List.of(history1, history2));
        List<HistoryGetDto> returnedHistoryList = historyService.getAllHistoies();
        assertNotNull(returnedHistoryList);
        assertEquals(2, returnedHistoryList.size());
    }

    @Test
    public void shouldFindHistoryByCurrencyAndDayPassed(){
        History history = utilities.buildHistory(1l,
                Utils.TEST_CURRENCY,
                Utils.TEST_DATE,
                Utils.TEST_OPEN,
                Utils.TEST_HIGH,
                Utils.TEST_LOW,
                Utils.TEST_CLOSE,
                Utils.TEST_VOLUME,
                Utils.TEST_MARKET_CAP);
        when(historyRepository.findHistoryByCurrency(Utils.TEST_CURRENCY)).thenReturn(List.of(history));
        HistoryGetDto historyGetDto = historyService.findHistoryByCurrencyAndDayPassed(Utils.TEST_CURRENCY,0);
        assertEquals(1L, historyGetDto.getId());
    }

    @Test
    public void shouldFindAllCurrencyType() {
        List<String> currencyTypes = new ArrayList<>();
        currencyTypes.add("bitCoin");
        currencyTypes.add("bitCoinNew");
        when(historyRepository.findAllCurrencyType()).thenReturn(currencyTypes);
        assertEquals("bitCoin", historyService.findAllCurrencyType().get(0));
        assertEquals("bitCoinNew", historyService.findAllCurrencyType().get(1));
    }
}
