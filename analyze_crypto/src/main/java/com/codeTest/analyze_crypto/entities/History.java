package com.codeTest.analyze_crypto.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="histories")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Long id;

    @Column(name="currency")
    private String currency;

    @Column(name="date")
    private String date;

    @Column(name="open")
    private Double open;

    @Column(name="high")
    private Double high;

    @Column(name="low")
    private Double low;

    @Column(name="close")
    private Double close;

    @Column(name="volume")
    private Long volume;

    @Column(name="market_cap")
    private Long market_cap;
}
