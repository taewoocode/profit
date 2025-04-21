package com.example.apitest.exchange;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class ExchangeUtilsTest {

	@Test
	@DisplayName("날짜 정보조회 테스트")
	void 날짜_테스트() throws Exception {
	    //given
		LocalDate currentDate = LocalDate.now();
		DayOfWeek dayOfWeek = getDayOfWeek(currentDate);
		log.info("dayofWeek={}", dayOfWeek);
	}

	private static DayOfWeek getDayOfWeek(LocalDate currentDate) {
		return currentDate.getDayOfWeek();
	}

}