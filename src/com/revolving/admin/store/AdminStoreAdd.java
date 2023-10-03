package com.revolving.admin.store;

import java.util.Scanner;

import com.revolving.Main;
import com.revolving.data.MenuData;
import com.revolving.data.StoreData;
import com.revolving.data.object.Menu;
import com.revolving.data.object.Store;

public class AdminStoreAdd {
	
	static Scanner scan;
	
	static {
		scan = new Scanner(System.in);
	}
	
	private String name;
	private String menuNo;
	private String address;
	private String tel;

	public void add() {
		Main.printMenu("매장 조회 > 매장 추가");
		
		name();
		menuNo();
		address();
		tel();
		
		modify();
		
		Store store = new Store((StoreData.list.size() + 1) + "", name, menuNo, "\"" + address + "\"", tel);
		StoreData.list.add(store);
		StoreData.save();
		
		System.out.println("매장 추가가 완료되었습니다.");
		System.out.println("아무거나 입력하면 매장 관리 페이지로 이동합니다.");
		scan.nextLine().trim();
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
	
	private void modify() {
		Main.printMenu("매장 추가 확인창");
		System.out.printf("1. 이름: %s\r\n", name);
		for (Menu menu : MenuData.list) {
			if (menu.getNo().equals(menuNo)) {
				System.out.printf("2. 대표 메뉴: %s\r\n", menu.getName());
				break;
			}
		}
		System.out.printf("3. 주소: %s\r\n", address);
		System.out.printf("4. 전화번호: %s\r\n", tel);
		Main.printLine();
		
		System.out.println("수정할 항목이 있습니까?");
		System.out.println("수정하시려면 수정할 항목의 번호를 누르세요.");
		Main.printLine();
		System.out.print("입력: ");
		String check = scan.nextLine().trim();
		
		if (check.equals("1")) {
			name();
		} else if (check.equals("2")) {
			menuNo();
		} else if (check.equals("3")) {
			address();
		} else if (check.equals("4")) {
			tel();
		} else {
			return;
		}
		
		modify();
	}
	
}
