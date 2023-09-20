package com.revolving.admin;

import java.util.Scanner;

import com.revolving.Main;

public class AdminMenu {
	
	static Scanner scan = new Scanner(System.in);
	
	public static void Menu() {
		
		Main.printMenu("관리자 메뉴");
		Main.printOption("메뉴 관리", "회원 정보 관리", "카테고리 관리", "사용자의 매장 및 음식 평가 관리");
		
		String input = scan.nextLine();
		
		if (input.equals("1")) {
			// 메뉴 관리
		} else if (input.equals("2")) {
			// 회원 정보 관리
		} else if (input.equals("3")) {
			// 카테고리 관리
		} else if (input.equals("4")) {
			// 사용자 평가 관리
		} else {
			return;
		}
		
	}

}
