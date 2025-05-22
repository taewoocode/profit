package com.profitkey.stock.service;

import com.profitkey.stock.dto.ExchangeDto;
import com.profitkey.stock.entity.ExchangeRate;
import com.profitkey.stock.repository.ExchangeRateRepository;
import com.profitkey.stock.util.DataConversionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExchangeRateService {
    private final ExchangeRateRepository exchangeRateRepository;

    @Transactional
    public void saveExchangeRates(List<ExchangeDto> exchangeDtoList) {
        LocalDate today = LocalDate.now();
        exchangeRateRepository.deleteByBaseDate(today);

        for (ExchangeDto dto : exchangeDtoList) {
            ExchangeRate exchangeRate = ExchangeRate.builder()
                .baseDate(today)
                .currencyCode(dto.getCur_unit())
                .currencyName(dto.getCur_nm())
                .exchangeRate(DataConversionUtil.toBigDecimal(dto.getDeal_bas_r()))
                .ttb(DataConversionUtil.toBigDecimal(dto.getTtb()))
                .tts(DataConversionUtil.toBigDecimal(dto.getTts()))
                .dealBasR(DataConversionUtil.toBigDecimal(dto.getDeal_bas_r()))
                .bkpr(DataConversionUtil.toBigDecimal(dto.getBkpr()))
                .build();

            exchangeRateRepository.save(exchangeRate);
        }
    }

    @Transactional(readOnly = true)
    public List<ExchangeRate> getExchangeRates(LocalDate date) {
        return exchangeRateRepository.findByBaseDate(date);
    }

    @Transactional(readOnly = true)
    public List<ExchangeRate> getExchangeRatesByCurrency(LocalDate date, String currencyCode) {
        return exchangeRateRepository.findByBaseDateAndCurrencyCode(date, currencyCode);
    }
} 