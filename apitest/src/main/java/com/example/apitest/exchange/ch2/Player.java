package com.example.apitest.exchange.ch2;

public class Player {
	public void training(HealthService healthService) {
		//헬스 서비스는
		Card card = new Card();
		healthService.hardTraining(card, 3000);
	}
}
