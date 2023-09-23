package com.revolving.member.mypage;

import java.util.Scanner;

import com.revolving.Main;

public class MyRate {

	static Scanner scan = new Scanner(System.in);
	
	public static void viewUserRate() {
		
		Main.printMenu("나의 매장 평가 조회");

		System.out.println("Enter를 누르면 이전 화면으로 돌아갑니다.");
		scan.nextLine();
	}
	
}
