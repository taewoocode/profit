package com.example.apitest.exchange.tes;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Wallet {

	private int money;

	public Wallet(int money) {
		this.money = money;
	}

	public void getWalletMoney(int amount) {
		System.out.println("돈을 꺼냅니다.");
	}
}
