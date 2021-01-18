package com.codeTest.analyze_crypto.utils;

import com.codeTest.analyze_crypto.dtos.history.HistoryGetDto;
import com.codeTest.analyze_crypto.entities.History;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {
    private static final int MONTH = 0;
    private static final int DAY = 1;
    private static final int YEAR = 2;

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
