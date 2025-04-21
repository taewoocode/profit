package com.example.apitest.exchange.tes;

public class Student {

	private Mother mother;

	public Student(Mother mother) {
		this.mother = mother;
	}

	public void learnFee(int amount) {
		//엄마는 학생에게 돈을 준다.
		mother.giveToMoney(amount);

	}
}
