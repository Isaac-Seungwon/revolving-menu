package com.revolving.member.mypage;

import java.util.Scanner;

import com.revolving.Main;

public class MyPageMenu {

	static Scanner scan = new Scanner(System.in);
	
	public static void viewUserMenu() {
		
		while (true) {
			Main.printMenu("마이페이지");
			Main.printOption("내가 선택한 메뉴", "매장 및 메뉴 평가", "회원 정보 수정");
			String input = scan.nextLine().trim();
			
			if (input.equals("1")) {
				MyStoreList.viewMyStoreList();
			} else if (input.equals("2")) {
				MyRate.viewUserRate();
			} else if (input.equals("3")) {
				ChangeInfo.changeUserInfo();
			} else {
				return;
			}
		}
	}
}
