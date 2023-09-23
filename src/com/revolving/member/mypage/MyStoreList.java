package com.revolving.member.mypage;

import java.util.Iterator;
import java.util.Scanner;

import com.revolving.Main;
import com.revolving.data.MemberData;
import com.revolving.data.MenuChooseData;
import com.revolving.data.MenuData;
import com.revolving.data.object.Member;
import com.revolving.data.object.Menu;
import com.revolving.data.object.MenuChoose;
import com.revolving.login.Login;

public class MyStoreList {

	static Scanner scan = new Scanner(System.in);

	public static void viewMyStoreList() {

		while (true) {
			Main.printMenu("내가 선택한 메뉴");
			Main.printOption("선택한 메뉴 기록 조회", "선택한 메뉴 기록 삭제");
			String input = scan.nextLine().trim();

			if (input.equals("1")) {

				Main.printMenu("선택한 메뉴 기록 조회");

				for (Member member : MemberData.list) {
					if (member.getId().equals(Login.user.getId())) {
						displayMemberMenuRecords(member);
						break;
					}
				}
				System.out.println("Enter를 누르면 이전 화면으로 돌아갑니다.");
				scan.nextLine();

			} else if (input.equals("2")) {
				Main.printMenu("선택한 메뉴 기록 삭제");
				
				for (Member member : MemberData.list) {
					if (member.getId().equals(Login.user.getId())) {
						displayMemberMenuRecords(member);
						break;
					}
				}
				
				deleteVisitedStoreRecord();

			} else {
				return;
			}
		}
	}

	private static void displayMemberMenuRecords(Member member) {
		int count = 1;
		
		for (MenuChoose menuChoose : MenuChooseData.list) {
			if (menuChoose.getMemberNo().equals(member.getNo())) {

				for (Menu menu : MenuData.list) {
					if (menu.getNo().equals(menuChoose.getMenuNo())) {
						System.out.printf("%d. 메뉴명: %s, 방문일: %s\n", count++, menu.getName(), menuChoose.getDate());
						break;
					}
				}
			}
		}
	}

	private static void deleteVisitedStoreRecord() {

		System.out.print("삭제할 메뉴 번호 입력: ");
		int choice = scan.nextInt();
		
		Main.printLine();

		int count = 1;

		for (Iterator<MenuChoose> iterator = MenuChooseData.list.iterator(); iterator.hasNext();) {

			MenuChoose menuChoose = iterator.next();

			if (menuChoose.getMemberNo().equals(Login.user.getNo())) {

				for (Menu menu : MenuData.list) {
					if (menu.getNo().equals(menuChoose.getMenuNo())) {

						if (count == choice) {
							iterator.remove();
							MenuChooseData.save();
							
							System.out.println("선택한 메뉴 기록이 삭제되었습니다.");

							System.out.println("Enter를 누르면 이전 화면으로 돌아갑니다.");
							scan.nextLine();
							scan.nextLine();
							return;
						}
						count++;
					}
				}
			}
		}

		System.out.println("Enter를 누르면 이전 화면으로 돌아갑니다.");
		scan.nextLine();
		scan.nextLine();
		return;
	}
}
