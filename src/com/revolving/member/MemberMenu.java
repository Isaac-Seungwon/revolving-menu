package com.revolving.member;

import java.util.Scanner;

import com.revolving.Main;
import com.revolving.member.mypage.MyPageMenu;

public class MemberMenu {

	static Scanner scan = new Scanner(System.in);

	public static void menu() {
		
		while (true) {
			Main.printMenu("회원 메뉴");
			Main.printOption("메뉴 추천", "메뉴 목록 정렬", "매장 평가 조회", "마이페이지");
			String input = scan.nextLine().trim();
			
			if (input.equals("1")) {
				MenuRecommend.menuRecommend();
			} else if (input.equals("2")) {
				MenuSort.mesuSort();
			} else if (input.equals("3")) {
				MenuRate.viewStoreReview();
			} else if (input.equals("4")) {
				MyPageMenu.viewUserMenu();
			} else if (input.equals("0")) {
				return;
			}
		}
	}
}
