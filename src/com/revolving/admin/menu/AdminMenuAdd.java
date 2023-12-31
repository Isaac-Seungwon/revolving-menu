package com.revolving.admin.menu;

import java.util.Scanner;

import com.revolving.Main;
import com.revolving.data.CategoryData;
import com.revolving.data.MenuData;
import com.revolving.data.object.Category;
import com.revolving.data.object.Menu;

public class AdminMenuAdd {
	
	static Scanner scan;
	
	static {
		scan = new Scanner(System.in);
	}
	
	private String name;
	private String category;
	private String weather;
	private String price;
	
	
	public void add() {
		
		Main.printMenu("메뉴 조회 > 메뉴 추가");
		
		name();
		category();
		weather();
		price();
		
		modify();
		
		Menu menu = new Menu((MenuData.list.size() + 1) + "", name, category, weather, price);
		MenuData.list.add(menu);
		MenuData.save();

		System.out.println("메뉴 추가가 완료되었습니다.");
		System.out.println("아무거나 입력하면 메뉴 관리 페이지로 이동합니다.");
		scan.nextLine().trim();
	}
	
	private void name() {
		Main.printMenu("이름");
		System.out.print("메뉴 이름: ");
		name = scan.nextLine().trim();
	}
	
	private void category() {
		Main.printMenu("카테고리");
		for (Category category : CategoryData.list) {
			System.out.printf("%s. %s\r\n", category.getNo(), category.getCookery());
		}
		Main.printLine();
		System.out.print("번호 입력: ");
		category = scan.nextLine().trim();
		
		if (category.matches("\\d+") && Integer.parseInt(category) >= 0 && Integer.parseInt(category) < CategoryData.list.size()) {}
		else {
			System.out.println("잘못 입력하셨습니다.");
			category();
		}
	}
	
	private void weather() {
		Main.printMenu("계절");
		System.out.println("0. 기타");
		System.out.println("1. 여름");
		System.out.println("2. 겨울");
		Main.printLine();
		System.out.print("번호 입력: ");
		
		weather = scan.nextLine().trim();
		
		if (weather.matches("\\d+") && Integer.parseInt(weather) >= 0 && Integer.parseInt(weather) < 3) {}
		else {
			System.out.println("잘못 입력하셨습니다.");
			weather();
		}
	}
	
	private void price() {
		Main.printMenu("가격");
		System.out.print("가격 입력(원): ");
		price = scan.nextLine().trim();
	}
	
	private void modify() {
		Main.printMenu("메뉴 추가 확인창");
		System.out.printf("1. 이름: %s\r\n", name);
		for(Category c : CategoryData.list) {
			if (c.getNo().equals(category)) {
				System.out.printf("2. 카테고리: %s\r\n", c.getCookery());
				break;
			}
		}
		System.out.printf("3. 계절: %s\r\n", weather);
		System.out.printf("4. 가격: %,d\r\n", Integer.parseInt(price));
		Main.printLine();

		System.out.println("수정할 항목이 있습니까?");
		System.out.println("수정하시려면 수정할 항목의 번호를 누르세요.");
		Main.printLine();
		System.out.print("입력: ");
		String check = scan.nextLine().trim();
		
		if (check.equals("1")) {
			name();
		} else if (check.equals("2")) {
			category();
		} else if (check.equals("3")) {
			weather();
		} else if (check.equals("4")) {
			price();
		} else {
			return;
		}
		
		modify();
	}
}