package com.codeTest.analyze_crypto.controllers;

import com.codeTest.analyze_crypto.CsvReader;
import com.codeTest.analyze_crypto.Headers;
import com.codeTest.analyze_crypto.dtos.history.HistoryGetDto;
import com.codeTest.analyze_crypto.dtos.history.HistoryPostDto;
import com.codeTest.analyze_crypto.services.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/histories")
public class HistoryController {
    private final HistoryService historyService;

    @GetMapping
    public ResponseEntity<List<HistoryGetDto>> getAll(){
        List<HistoryGetDto> historyGetDtos = historyService.getAllHistoies();
        return ResponseEntity.ok(historyGetDtos);
    }

    @PostMapping("/init")
    public ResponseEntity<Object> init() {
        String headers[] = new String[Headers.values().length];
        for(Headers header: Headers.values())
            headers[header.getIndex()] = header.getName();

        List<String[]> data = CsvReader.readCSV("static/crypto_historical_data.csv",headers);

        for(String[] row: data){
            HistoryPostDto historyPostDto = new HistoryPostDto();
            historyPostDto.setCurrency(row[0].replaceAll(",",""));
            historyPostDto.setDate(row[1].replaceAll(",",""));
            historyPostDto.setOpen(Double.valueOf(row[2].replaceAll(",","")));
            historyPostDto.setHigh(Double.valueOf(row[3].replaceAll(",","")));
            historyPostDto.setLow(Double.valueOf(row[4].replaceAll(",","")));
            historyPostDto.setClose(Double.valueOf(row[5].replaceAll(",","")));
            historyPostDto.setVolume(Long.valueOf(row[6].replaceAll(",","")));
            historyPostDto.setMarket_cap(Long.valueOf(row[7].replaceAll(",","")));
            historyService.create(historyPostDto);
        }
        return ResponseEntity.ok("");
    }

    @PostMapping("/add")
    public ResponseEntity<HistoryGetDto> add(@RequestBody HistoryPostDto historyPostDto){
        return ResponseEntity.ok(historyService.create(historyPostDto));
    }

    @GetMapping(value = "/latest")
    public ResponseEntity<List<HistoryGetDto>> getLatestHistories(){
        return ResponseEntity.ok(historyService.findLatestHistories());
    }

    @GetMapping(value = "/search", params = {"currency","dayPassed"})
    public ResponseEntity<HistoryGetDto> getHistoryByCurrencyAndDayPassed(@RequestParam String currency, @RequestParam String dayPassed){
        return ResponseEntity.ok(historyService.findHistoryByCurrencyAndDayPassed(currency, Integer.valueOf(dayPassed)));
    }

    @GetMapping("/currencies")
    public ResponseEntity<List<String>> getCurrencyTypes(){
        return ResponseEntity.ok(historyService.findAllCurrencyType());
    }
}
