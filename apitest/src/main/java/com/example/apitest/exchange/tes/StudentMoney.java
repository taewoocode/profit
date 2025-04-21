package com.example.apitest.exchange.tes;

public class StudentMoney {
	public static void main(String[] args) {
		Wallet wallet = new Wallet(3000);
		Mother mother = new Mother(wallet);
		Student student = new Student(mother);
		Teacher teacher = new Teacher(student);
		Chiken chiken = new Chiken();
		teacher.eatSomeThing(chiken, "양념치킨");

	}

}
