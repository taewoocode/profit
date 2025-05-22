package com.profitkey.stock.controller;

import com.profitkey.stock.entity.ExchangeRate;
import com.profitkey.stock.service.ExchangeRateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/exchange-rates")
@RequiredArgsConstructor
@Tag(name = "Exchange Rate API", description = "환율 정보 API")
public class ExchangeRateController {
    private final ExchangeRateService exchangeRateService;

    @GetMapping
    @Operation(summary = "환율 정보 조회", description = "특정 날짜의 모든 환율 정보를 조회합니다.")
    public ResponseEntity<List<ExchangeRate>> getExchangeRates(
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        if (date == null) {
            date = LocalDate.now();
        }
        return ResponseEntity.ok(exchangeRateService.getExchangeRates(date));
    }

    @GetMapping("/{currencyCode}")
    @Operation(summary = "특정 통화의 환율 정보 조회", description = "특정 날짜의 특정 통화에 대한 환율 정보를 조회합니다.")
    public ResponseEntity<List<ExchangeRate>> getExchangeRatesByCurrency(
        @PathVariable String currencyCode,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        if (date == null) {
            date = LocalDate.now();
        }
        return ResponseEntity.ok(exchangeRateService.getExchangeRatesByCurrency(date, currencyCode));
    }
} 