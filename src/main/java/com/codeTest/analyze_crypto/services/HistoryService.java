package com.codeTest.analyze_crypto.services;

import com.codeTest.analyze_crypto.dtos.history.HistoryGetDto;
import com.codeTest.analyze_crypto.dtos.history.HistoryPostDto;
import com.codeTest.analyze_crypto.entities.History;
import com.codeTest.analyze_crypto.mappers.HistoryMapper;
import com.codeTest.analyze_crypto.repositories.HistoryRepository;
import com.codeTest.analyze_crypto.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HistoryService {
    private final HistoryRepository historyRepository;
    private final HistoryMapper historyMapper;

    public List<HistoryGetDto> getAllHistoies(){
        return historyRepository.findAll().stream()
                .map(historyMapper::fromEntity)
                .collect(Collectors.toList());
    }

    public HistoryGetDto create(HistoryPostDto historyPostDto) {
        History historyEntity = historyMapper.toEntity(historyPostDto);
        return historyMapper.fromEntity(historyRepository.save(historyEntity));
    }

    public List<HistoryGetDto> findLatestHistories(){
        List<HistoryGetDto> latestHistoryGetDtos = new ArrayList<>();
        List<String> currencies = findAllCurrencyType();
        for(String currency: currencies){
            HistoryGetDto latestHistoryGetDto = findHistoryByCurrencyAndDayPassed(currency, 0);
            latestHistoryGetDtos.add(latestHistoryGetDto);
        }

        return  latestHistoryGetDtos;
    }

    public HistoryGetDto findHistoryByCurrencyAndDayPassed(String currency, int dayPassed){
        List<HistoryGetDto> historyGetDtos = historyMapper.fromEntities(historyRepository.findHistoryByCurrency(currency));
        List<HistoryGetDto> sortedHistories = Utils.sortHistoryByDate(historyGetDtos);
        HistoryGetDto resultHistoryGetDto = sortedHistories.get(dayPassed);

        return  resultHistoryGetDto;
    }

    public List<String> findAllCurrencyType(){
        List<String> currencyTypes = historyRepository.findAllCurrencyType();
        return currencyTypes;
    }
}
