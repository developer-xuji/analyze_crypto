package com.codeTest.analyze_crypto.dtos.history;

import lombok.Data;

import javax.persistence.Column;

@Data
public class HistoryGetDto {
    private Long id;
    private String currency;
    private String date;
    private Double open;
    private Double high;
    private Double low;
    private Double close;
    private Long volume;
    private Long market_cap;
}
