package com.example.apitest.exchange.good;

public class GoodService {
	public static void main(String[] args) {
		Coach coach = new Coach();
		PlayerSoccer playerSoccer = new PlayerSoccer();
		coach.instructTraining(playerSoccer);
	}
}
