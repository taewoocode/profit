package com.example.apitest.exchange.tes;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Mother {

	private Wallet wallet;

	public Mother(Wallet wallet) {
		this.wallet = wallet;
	}

	public void giveToMoney(int amount) {
		System.out.println("엄마는 지갑에서 돈을 꺼낸다.");
		wallet.getWalletMoney(amount);
	}
}
