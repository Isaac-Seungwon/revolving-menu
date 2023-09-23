package com.revolving.member.mypage;

import java.util.Scanner;

import com.revolving.Main;

public class MyRate {

	static Scanner scan = new Scanner(System.in);
	
	public static void viewUserRate() {
		while (true) {
			Main.printMenu("매장 및 메뉴 평가");
			Main.printOption("매장 및 메뉴 평가 조회", "매장 및 메뉴 평가 삭제");
			String input = scan.nextLine().trim();
			
			if (input.equals("1")) {
				
			} else if (input.equals("2")) {
				
			} else {
				return;
			}
		}
	}
	
}
