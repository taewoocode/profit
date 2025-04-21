package com.example.apitest.exchange.ch2;

public class Coach {

	/**
	 * Coach -> Player -> HealthService -> Card
	 */

	/**
	 * 감독은 선수에게 훈련을 하라는 명령을 내린다.
	 */
	public void commandTraining(Player player) {
		System.out.println("운동을 시작해라");
		player.training(new HealthService());
	}
}
