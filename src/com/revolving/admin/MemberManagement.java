package com.revolving.admin;

import java.util.ArrayList;
import java.util.Scanner;

import com.revolving.Main;
import com.revolving.data.MemberData;
import com.revolving.data.MenuChooseData;
import com.revolving.data.MenuData;
import com.revolving.data.RatingData;
import com.revolving.data.StoreData;
import com.revolving.data.object.Member;
import com.revolving.data.object.Menu;
import com.revolving.data.object.MenuChoose;
import com.revolving.data.object.Rating;
import com.revolving.data.object.Store;

public class MemberManagement {

	static Scanner scan;
	static private int currentIndex;
	static private ArrayList<Member> list;
	private String name;

	static {
		list = new ArrayList<Member>();
		scan = new Scanner(System.in);
	}

	public void menu() {

		while (true) {

			Main.printMenu("회원 관리");
			Main.printOption("전체 조회", "회원 검색");

			String input = scan.nextLine().trim();

			if (input.equals("1")) {
				allSort();
			} else if (input.equals("2")) {
				search();
			} else if (input.equals("0")) {
				return;
			} else {
				System.out.println("잘못 입력하셨습니다.");
			}

		}

	}

	private void search() {

		Main.printMenu("회원 조회 > 회원 검색");
		Main.printOption("회원번호 검색", "이름 검색", "아이디 검색");

		String input = scan.nextLine();

		if (input.equals("1")) {
			memberNo();
		} else if (input.equals("2")) {
			name();
		} else if (input.equals("3")) {
			id();
		} else if (input.equals("0")) {
			return;
		} else {
			System.out.println("잘못 입력하셨습니다.");
			search();
		}

	}

	private void id() {
		
		Main.printLine();
		System.out.print("아이디 입력: ");
		String input = scan.nextLine().trim();
		
		for (Member member : MemberData.list) {
			if (member.getId().equals(input)) {
				sortResort(member.getNo());
				return;
			}
		}
		
		System.out.println("잘못 입력하셨습니다.");
		search();
	}

	private void name() {
		
		Main.printLine();
		System.out.print("이름 입력: ");
		String input = scan.nextLine().trim();
		
		for (Member member : MemberData.list) {
			if (member.getName().contains(input)) {
				System.out.printf("회원번호: %s\r\n", member.getNo());
				System.out.printf("회원명: %s\r\n", member.getName());
				System.out.printf("회원아이디: %s\r\n", member.getId());
				System.out.println();
			}
		}
		memberNo();
	}

	private void memberNo() {
		
		Main.printLine();
		System.out.print("원하는 회원번호 입력: ");
		String input = scan.nextLine().trim();
		
		if (input.matches("\\d+") && Integer.parseInt(input) > 0 && Integer.parseInt(input) < MemberData.list.size() + 1) {
			sortResort(input);
		} else {
			System.out.println("잘못 입력하셨습니다.");
			memberNo();
		}
		
	}

	private void allSort() {

		for (Member member : MemberData.list) {
			if (member.getName().equals("-")) {
				continue;
			}
			list.add(member);
		}

		while (true) {
			Main.printMenu("회원 전체 조회");

			int endIndex = Math.min(currentIndex + 20, list.size());

			for (int i = currentIndex; i < endIndex; i++) {
				Member member = list.get(i);

				System.out.printf("회원번호: %s\r\n", member.getNo());
				System.out.printf("회원명: %s\r\n", member.getName());

				if (i != endIndex - 1) {
					System.out.println();
				}
			}

			if (!choiceMenu(list, endIndex)) {
				list.clear();
				return;
			}
		}

	}

	private void sortResort(String number) {
		Main.printLine();

		for (Member member : MemberData.list) {
			if (member.getNo().equals(number)) {
				name = member.getName();
				System.out.printf("회원번호: %s\r\n", member.getNo());
				System.out.printf("회원명: %s\r\n", member.getName());
				System.out.printf("회원아이디: %s\r\n", member.getId());
				System.out.printf("전화번호: %s\r\n", member.getTel());
				System.out.printf("생년월일: %s\r\n", member.getBirth());
				break;
			}
		}
		Main.printLine();
		Main.printOption("선택한 메뉴 조회", "평가 조회", "회원 탈퇴");
		
		String input = scan.nextLine().trim();
		
		if (input.equals("1")) {
			menuChoose(number);
		} else if (input.equals("2")) {
			storeReview(number);
		} else if (input.equals("3")) {
			delete(number);
		} else if (input.equals("0")) {
			return;
		} else {
			System.out.println("잘못 입력하셨습니다.");
			sortResort(number);
		}

	}

	private void delete(String number) {
		Member member = MemberData.list.get(Integer.parseInt(number) - 1);
		
		System.out.println("정말 삭제하시겠습니까?");
		System.out.println("삭제하려면 y, 아니면 n을 눌러주세요.");
		
		String input = scan.nextLine().trim();
		
		if (input.equals("y") || input.equals("Y")) {
			member.setName("-");
			MemberData.save();
			return;
		} else if (input.equals("n") || input.equals("N")) {
			return;
		} else {
			System.out.println("잘못 입력하셨습니다.");
			delete(number);
		}
		
	}

	
	private void storeReview(String number) {
		
		Main.printMenu(name + " 평가 조회");
		
		for (Rating rating : RatingData.list) {
			if (rating.getMemberNo().equals(number)) {
				for (Store store : StoreData.list) {
					if (store.getNo().equals(rating.getStoreNo())) {
						System.out.printf("매장명: %s\r\n", store.getName());
						break;
					}
				}
				System.out.printf("리뷰 내용: %s\r\n", rating.getReview());
				System.out.printf("점수: %s\r\n", rating.getScore());
				System.out.println();
			}
		}
		scan.nextLine().trim();
		
	}

	private void menuChoose(String number) {
		
		Main.printMenu(name + " 선택한 메뉴 조회");
		
		for (MenuChoose menuChoose : MenuChooseData.list) {
			if (menuChoose.getMemberNo().equals(number)) {
				for (Menu menu : MenuData.list) {
					if (menu.getNo().equals(menuChoose.getMenuNo())) {
						System.out.printf("메뉴이름: %s\r\n", menu.getName());
						break;
					}
				}
				System.out.printf("선택한 날: %s\r\n", menuChoose.getDate());
				System.out.println();
			}
		}
		scan.nextLine().trim();
		
	}

	private boolean choiceMenu(ArrayList<Member> list, int endIndex) {

		while (true) {
			Main.printLine();
			if (endIndex < list.size()) {
				System.out.println("1. 다음 회원 보기");
			}
			if (currentIndex > 0) {
				System.out.println("2. 이전 회원 보기");
			}
			System.out.println("3. 회원 선택");
			System.out.println("0. 돌아가기");
			System.out.print("번호 입력: ");
			String input = scan.nextLine().trim();

			if (input.equals("1")) {
				if (endIndex < list.size()) {
					currentIndex += 20;
					return true;
				} else {
					System.out.println("다음 회원이 없습니다.");
					scan.nextLine();
				}
			} else if (input.equals("2")) {
				if (currentIndex > 0) {
					currentIndex = Math.max(0, currentIndex - 20);
					return true;
				} else {
					System.out.println("이전 회원이 없습니다.");
					scan.nextLine();
				}
			} else if (input.equals("3")) {
				Main.printLine();
				System.out.print("회원 번호 입력: ");
				input = scan.nextLine().trim();

				if (input.matches("\\d+") && Integer.parseInt(input) > 0
						&& Integer.parseInt(input) <= MemberData.list.size()) {
					sortResort(input);
					return false;
				} else {
					System.out.println("잘못 입력하셨습니다.");
				}
			} else if (input.equals("0")) {
				System.out.println("Enter를 누르면 이전 화면으로 돌아갑니다.");
				scan.nextLine();
				return false;
			} else {
				System.out.println("잘못 입력하셨습니다.");
			}
		}
	}

}