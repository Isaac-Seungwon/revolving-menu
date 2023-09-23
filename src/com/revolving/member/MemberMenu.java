package com.revolving.member;

import java.util.Scanner;

import com.revolving.Main;
import com.revolving.login.Login;
import com.revolving.member.mypage.MyPageMenu;

public class MemberMenu {

	static Scanner scan = new Scanner(System.in);

	public static void menu() {
		
		while (true) {
			Main.printMenu("회원 메뉴");
			Main.printOption("메뉴 추천", "메뉴 목록 정렬", "후기 작성", "매장 평가 조회", "마이페이지");
			String input = scan.nextLine().trim();
			
			if (input.equals("1")) {
				
			} else if (input.equals("2")) {
				
			} else if (input.equals("3")) {
				
			} else if (input.equals("4")) {
				MenuRate.viewStoreReview();
			} else if (input.equals("5")) {
				MyPageMenu.viewUserMenu();
			} else {
				return;
			}
		}
	}
}
