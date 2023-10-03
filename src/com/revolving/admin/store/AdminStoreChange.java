package com.revolving.admin.store;

import java.util.Scanner;

import com.revolving.Main;
import com.revolving.data.MenuData;
import com.revolving.data.StoreData;
import com.revolving.data.object.Menu;
import com.revolving.data.object.Store;

public class AdminStoreChange {
	
	static Scanner scan;
	
	static {
		scan = new Scanner(System.in);
	}
	
	private String name;
	private String menuNo;
	private String address;
	private String tel;
	
	public void change(String input) {
		
		while (true) {
			Main.printMenu("매장 수정 / 삭제");
			
			Store store = StoreData.list.get(Integer.parseInt(input) - 1);
			
			System.out.printf("1. 매장명: %s\r\n", store.getName());
			for (Menu menu : MenuData.list) {
				if (menu.getNo().equals(store.getMenuNo())) {
					System.out.printf("2. 대표메뉴: %s\r\n", menu.getName());
				}
				break;
			}
			System.out.printf("3. 매장주소: %s\r\n", store.getAddress());
			System.out.printf("4. 전화번호: %s\r\n", store.getTel());
			
			Main.printLine();
			Main.printOption("수정", "삭제");
			
			String choose = scan.nextLine().trim();
			
			if (choose.equals("1")) {
				modify(store);
			} else if (choose.equals("2")) {
				delete(store);
				return;
			} else if (choose.equals("0")) {
				return;
			} else {
				System.out.println("잘못 입력하셨습니다.");
			}
		}
		
	}

	private void delete(Store store) {
		
		System.out.println("정말 삭제하시겠습니까?");
		System.out.println("삭제하려면 y, 아니면 n을 눌러주세요.");
		
		String input = scan.nextLine().trim();
		
		if (input.equals("y") || input.equals("Y")) {
			store.setName("-");
			StoreData.save();
			return;
		} else if (input.equals("n") || input.equals("N")) {
			return;
		} else {
			System.out.println("잘못 입력하셨습니다.");
			delete(store);
		}
		
	}

	private void modify(Store store) {
		
		System.out.println("수정할 항목의 번호를 누르세요.");
		Main.printLine();
		System.out.print("입력: ");
		String check = scan.nextLine().trim();
		
		if (check.equals("1")) {
			name();
			store.setName(name);
		} else if (check.equals("2")) {
			menuNo();
			store.setMenuNo(menuNo);
		} else if (check.equals("3")) {
			address();
			store.setAddress("\"" + address + "\"");
		} else if (check.equals("4")) {
			tel();
			store.setTel(tel);
		} else {
			StoreData.save();
			return;
		}
		
	}
	
	private void name() {
		Main.printMenu("이름");
		System.out.print("매장 이름: ");
		name = scan.nextLine().trim();
	}
	
	private void menuNo() {
		Main.printMenu("대표 메뉴");
		System.out.print("메뉴 번호: ");
		menuNo = scan.nextLine().trim();
		
		if (menuNo.matches("\\d+") && Integer.parseInt(menuNo) > 0 && Integer.parseInt(menuNo) < MenuData.list.size() + 1) {
			Menu menu = MenuData.list.get(Integer.parseInt(menuNo) - 1);
			
			if (menu.getName().equals("-")) {
				System.out.println("잘못된 입력입니다.");
				menuNo();
			}
		} else {
			System.out.println("잘못된 입력입니다.");
			menuNo();
		}
	}
	
	private void address() {
		Main.printMenu("주소");
		System.out.print("주소: ");
		address = scan.nextLine().trim();
	}
	
	private void tel() {
		Main.printMenu("전화번호");
		System.out.print("전화번호: ");
		tel = scan.nextLine().trim();
	}

}
