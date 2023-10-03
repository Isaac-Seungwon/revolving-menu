package com.revolving.admin.menu;

import java.util.Scanner;

import com.revolving.Main;
import com.revolving.data.CategoryData;
import com.revolving.data.MenuData;
import com.revolving.data.object.Category;
import com.revolving.data.object.Menu;

public class AdminMenuChange {
	
	static Scanner scan;
	
	static {
		scan = new Scanner(System.in);
	}
	
	private String name;
	private String category;
	private String weather;
	private String price;

	public void change(String input) {
		
		while (true) {
			Main.printMenu("메뉴 수정 / 삭제");
			
			Menu menu = MenuData.list.get(Integer.parseInt(input) - 1);
			
			System.out.printf("1. 메뉴명: %s\r\n", menu.getName());
			for (Category category : CategoryData.list) {
				if (category.getNo().equals(menu.getCategoryNo())) {
					System.out.printf("2. 카테고리: %s\r\n", category.getCookery());
				}
			}
			if (menu.getSeasonNo().equals("0")) {
				System.out.println("3. 시즌: 기타");
			} else if (menu.getSeasonNo().equals("1")) {
				System.out.println("3. 시즌: 여름");
			} else {
				System.out.println("3. 시즌: 겨울");
			}
			System.out.printf("4. 가격: %s원\r\n", menu.getPrice());
			
			Main.printLine();
			Main.printOption("수정", "삭제");
			
			String choose = scan.nextLine().trim();
			
			if (choose.equals("1")) {
				modify(menu);
			} else if (choose.equals("2")) {
				delete(menu);
				return;
			} else if (choose.equals("0")) {
				return;
			} else {
				System.out.println("잘못 입력하셨습니다.");
			}
		}
	}

	private void delete(Menu menu) {
		
		System.out.println("정말 삭제하시겠습니까?");
		System.out.println("삭제하려면 y, 아니면 n을 눌러주세요.");
		
		String input = scan.nextLine().trim();
		
		if (input.equals("y") || input.equals("Y")) {
			menu.setName("-");
			MenuData.save();
			return;
		} else if (input.equals("n") || input.equals("N")) {
			return;
		} else {
			System.out.println("잘못 입력하셨습니다.");
			delete(menu);
		}
		
	}

	private void modify(Menu menu) {
		
		System.out.println("수정할 항목의 번호를 누르세요.");
		Main.printLine();
		System.out.print("입력: ");
		String check = scan.nextLine().trim();
		
		if (check.equals("1")) {
			name();
			menu.setName(name);
		} else if (check.equals("2")) {
			category();
			menu.setCategoryNo(category);
		} else if (check.equals("3")) {
			weather();
			menu.setSeasonNo(weather);
		} else if (check.equals("4")) {
			price();
			menu.setPrice(price);
		} else {
			MenuData.save();
			return;
		}
		
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
	
}
