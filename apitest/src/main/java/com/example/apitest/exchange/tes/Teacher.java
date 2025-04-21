package com.example.apitest.exchange.tes;


public class Teacher {

	//참조 방향성 (메시지의 방향성)
	/**
	 * 선생님 -> 학생 -> 엄마 -> 지갑
	 */

	public Student student;

	public Teacher(Student student) {
		this.student = student;
	}

	public void teachingStudent(int amount) {
		//학생은 수업을 받으면 돈을 지불해야 한다.
		System.out.println("수업을 시작합니다.");
		student.learnFee(amount);
	}

	/**
	 * 선생님이 먹는 메서드
	 * @param chiken
	 * @param message
	 */
	public void eatSomeThing(Chiken chiken, String message) {
		System.out.println("message: " + message);
		chiken.readyForEatten(message);

	}
}
