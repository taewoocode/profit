package com.example.apitest.exchange.good;

import com.example.apitest.exchange.ch2.Player;

public class Coach {

	private PlayerSoccer playerSoccer;

	public void instructTraining(PlayerSoccer player) {
		player.hardTraining(new GymService());
		System.out.println("1");
	}

	public void instructTraining2(GymService service) {
		playerSoccer.hardTraining(service);
	}
}
