package com.profitkey.stock.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "exchange_rate")
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "base_date", nullable = false)
    private LocalDate baseDate;

    @Column(name = "currency_code", nullable = false)
    private String currencyCode;

    @Column(name = "currency_name", nullable = false)
    private String currencyName;

    @Column(name = "exchange_rate", nullable = false, precision = 10, scale = 2)
    private BigDecimal exchangeRate;

    @Column(name = "ttb", precision = 10, scale = 2)
    private BigDecimal ttb;

    @Column(name = "tts", precision = 10, scale = 2)
    private BigDecimal tts;

    @Column(name = "deal_bas_r", precision = 10, scale = 2)
    private BigDecimal dealBasR;

    @Column(name = "bkpr", precision = 10, scale = 2)
    private BigDecimal bkpr;

    @Builder
    public ExchangeRate(LocalDate baseDate, String currencyCode, String currencyName,
                       BigDecimal exchangeRate, BigDecimal ttb, BigDecimal tts,
                       BigDecimal dealBasR, BigDecimal bkpr) {
        this.baseDate = baseDate;
        this.currencyCode = currencyCode;
        this.currencyName = currencyName;
        this.exchangeRate = exchangeRate;
        this.ttb = ttb;
        this.tts = tts;
        this.dealBasR = dealBasR;
        this.bkpr = bkpr;
    }
} 