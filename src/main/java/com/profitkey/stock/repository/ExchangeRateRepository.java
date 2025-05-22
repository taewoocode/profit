package com.profitkey.stock.repository;

import com.profitkey.stock.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {
    List<ExchangeRate> findByBaseDate(LocalDate baseDate);
    List<ExchangeRate> findByBaseDateAndCurrencyCode(LocalDate baseDate, String currencyCode);
    void deleteByBaseDate(LocalDate baseDate);
} 