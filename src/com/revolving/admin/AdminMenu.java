package com.revolving.admin;

import java.util.Scanner;

import com.revolving.Main;

public class AdminMenu {
	
	static Scanner scan = new Scanner(System.in);
	
	public static void menu() {
		
		while(true) {
			Main.printMenu("관리자 메뉴");
			Main.printOption("메뉴 관리", "매장 관리", "회원 정보 관리", "카테고리 관리", "사용자의 매장 및 음식 평가 관리");
			
			String input = scan.nextLine();
			
			if (input.equals("1")) {
				MenuManagement.menu();
			} else if (input.equals("2")) {
				StoreManagement.store();
			} else if (input.equals("3")) {
				MemberManagement memberManagement = new MemberManagement();
				memberManagement.menu();
			} else if (input.equals("4")) {
				// 카테고리 관리
			} else if (input.equals("5")) {
				// 사용자 평가 관리
			} else {
				return;
			}
		}
	}
	
}
