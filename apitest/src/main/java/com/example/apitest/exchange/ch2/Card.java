package com.example.apitest.exchange.ch2;

public class Card {
	private int money;

	public void receivedMoney(int amount) {
		money += amount;
		System.out.println(amount + "가 입금되었습니다." + "현재잔액: " + money);

	}
}
