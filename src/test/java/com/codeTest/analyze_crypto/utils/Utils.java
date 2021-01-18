package com.codeTest.analyze_crypto.utils;

import com.codeTest.analyze_crypto.dtos.history.HistoryGetDto;
import com.codeTest.analyze_crypto.dtos.history.HistoryPostDto;
import com.codeTest.analyze_crypto.entities.History;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {
    public static final String TEST_DATE = "Dec 04 2019";
    public static final String TEST_LATESTDATE = "Dec 06 2019";
    public static final String TEST_CURRENCY = "bitCoin";
    public static final String TEST_CURRENCY_New = "bitCoinNew";
    public static final Double TEST_OPEN = 1.0;
    public static final Double TEST_HIGH = 2.0;
    public static final Double TEST_LOW = 3.0;
    public static final Double TEST_CLOSE = 4.0;
    public static final Long TEST_VOLUME = 10L;
    public static final Long TEST_MARKET_CAP = 20L;

    private static final int MONTH = 0;
    private static final int DAY = 1;
    private static final int YEAR = 2;

    public HistoryGetDto buildHistoryGetDto( Long id,
                                             String currency,
                                             String date,
                                             Double open,
                                             Double high,
                                             Double low,
                                             Double close,
                                             Long volume,
                                             Long market_cap){
        HistoryGetDto historyGetDto = new HistoryGetDto();
        historyGetDto.setId(id);
        historyGetDto.setCurrency(currency);
        historyGetDto.setDate(date);
        historyGetDto.setOpen(open);
        historyGetDto.setHigh(high);
        historyGetDto.setLow(low);
        historyGetDto.setClose(close);
        historyGetDto.setVolume(volume);
        historyGetDto.setMarket_cap(market_cap);

        return historyGetDto;
    }

    public HistoryPostDto buildHistoryPostDto(
                                             String currency,
                                             String date,
                                             Double open,
                                             Double high,
                                             Double low,
                                             Double close,
                                             Long volume,
                                             Long market_cap){
        HistoryPostDto historyPostDto = new HistoryPostDto();
        historyPostDto.setCurrency(currency);
        historyPostDto.setDate(date);
        historyPostDto.setOpen(open);
        historyPostDto.setHigh(high);
        historyPostDto.setLow(low);
        historyPostDto.setClose(close);
        historyPostDto.setVolume(volume);
        historyPostDto.setMarket_cap(market_cap);

        return historyPostDto;
    }

    public History buildHistory(Long id,
                                String currency,
                                String date,
                                Double open,
                                Double high,
                                Double low,
                                Double close,
                                Long volume,
                                Long market_cap) {
        History history = new History();
        history.setId(id);
        history.setCurrency(currency);
        history.setDate(date);
        history.setOpen(open);
        history.setHigh(high);
        history.setLow(low);
        history.setClose(close);
        history.setVolume(volume);
        history.setMarket_cap(market_cap);
        return history;
    }

    public static Map<String, Integer> getDate(String dateString){
        String[] dateStrings = dateString.split(" ");
        int year = Integer.valueOf(dateStrings[YEAR]);
        int day = Integer.valueOf(dateStrings[DAY]);
        int month = 0;
        switch (dateStrings[MONTH]){
            case "Oct":
                month = 10;
                break;
            case "Nov":
                month = 11;
                break;
            case  "Dec":
                month = 12;
                break;
            default:
        }
        Map<String, Integer> resultDate = new HashMap<String, Integer>();
        resultDate.put("year",year);
        resultDate.put("month",month);
        resultDate.put("day",day);

        return  resultDate;
    }

    //return true if leftDate is bigger
    public static boolean compareDate(Map<String, Integer> leftDateMap, Map<String, Integer> rightDateMap){
        if(leftDateMap.get("year") > rightDateMap.get("year"))
            return true;
        if(leftDateMap.get("year") < rightDateMap.get("year"))
            return false;

        if(leftDateMap.get("month") > rightDateMap.get("month"))
            return true;
        if(leftDateMap.get("month") < rightDateMap.get("month"))
            return false;

        if(leftDateMap.get("day") > rightDateMap.get("day"))
            return true;
        if(leftDateMap.get("day") < rightDateMap.get("day"))
            return false;
        return false;
    }


    public static List<HistoryGetDto> sortHistoryByDate(List<HistoryGetDto> historyGetDtos){
        List<HistoryGetDto> sortedHistoryGetDtos = new ArrayList<>();
        List<HistoryGetDto> originlHistoryGetDtos = new ArrayList<>();
        originlHistoryGetDtos.addAll(historyGetDtos);

        while (!originlHistoryGetDtos.isEmpty()){
            HistoryGetDto latestHistoryGetDto = originlHistoryGetDtos.get(0);
            for(HistoryGetDto historyGetDto: originlHistoryGetDtos){
                Map<String,Integer> dateMap = Utils.getDate(historyGetDto.getDate());
                Map<String,Integer> latestDateMap = Utils.getDate(latestHistoryGetDto.getDate());

                if(Utils.compareDate(dateMap, latestDateMap))
                    latestHistoryGetDto = historyGetDto;
            }
            sortedHistoryGetDtos.add(latestHistoryGetDto);
            originlHistoryGetDtos.remove(latestHistoryGetDto);
        }
        return sortedHistoryGetDtos;
    }
}
