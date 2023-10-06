package com.revolving.member;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.revolving.Main;
import com.revolving.data.MenuChooseData;
import com.revolving.data.MenuData;
import com.revolving.data.object.Menu;
import com.revolving.data.object.MenuChoose;
import com.revolving.login.Login;

public class MenuRecommend {

	static Scanner scan = new Scanner(System.in);
	
	public static void menuRecommend() {
		
		Main.printMenu("메뉴 추천 기능 선택");
		Main.printOption("무작위 메뉴 추천", "카테고리 메뉴 추천", "계절별 제철 음식 추천");

		// 메뉴 랜덤 무작위 1개 추첨
		// 카테고리 선택
		// 관련 메뉴 랜덤 5개
		// 계절 선택
		// 관련 메뉴 랜덤 3개

		String sel = scan.nextLine();

		if (sel.equals("1")) {
			randomMenu();

		} else if (sel.equals("2")) {
			categoryRandom();

		} else if (sel.equals("3")) {
			seasonRandom();

		} else {
			System.out.println("번호를 다시 입력해주세요.");
			Main.printLine();
			System.out.println("번호 입력");
		}
	}

	private static void seasonRandom() {
		Main.printMenu("계절 선택");
		Main.printOption("여름", "겨울", "기타");
		String select = scan.nextLine();

		rndSeasonList(select);
	}

	private static void rndSeasonList(String select) {
		List<Menu> sesonList = new ArrayList<>();
		
		for (Menu seasonMenu : MenuData.list) {
			if (select.equals(String.valueOf(seasonMenu.getSeasonNo()))) {
				sesonList.add(seasonMenu);
			}
		}
		
		// 계절 메뉴 추천
		while (true) {
			List<Menu> rndSesonList = new ArrayList<>();
			
			// 랜덤하게 메뉴를 선택하여 출력
			Random random = new Random();
			int i = 0;
	
			for (int count = 0; count < 3; count++) {
				i++;
				int randomIndex = random.nextInt(sesonList.size());
				Menu rndSeasonMenu = sesonList.get(randomIndex);
				System.out.printf("%d. " + rndSeasonMenu.getName() + "\n", i);
				
				rndSesonList.add(rndSeasonMenu);
			}
	
			System.out.println("4. 새로운 메뉴 추천");
			System.out.println("0. 돌아가기");
			Main.printLine();
			System.out.print("번호 입력: ");
			String input = scan.nextLine();
			
			if (input.equals("0")) {
		        return; // Go back
		    } else {
		        if (Integer.parseInt(input) > 0 && Integer.parseInt(input) < rndSesonList.size() + 1) {
			        int selectedMenuIndex = Integer.parseInt(input) - 1;
		            Menu selectedMenu = rndSesonList.get(selectedMenuIndex);
		            String todayDate = new SimpleDateFormat("yyMMdd").format(new Date());
		            MenuChoose newMenuChoose = new MenuChoose(String.valueOf(MenuChooseData.list.size() + 1), Login.user.getNo(), selectedMenu.getNo(), todayDate);
		            MenuChooseData.list.add(newMenuChoose);
		            MenuChooseData.save();
		            
		            System.out.println("선택한 메뉴: " + selectedMenu.getName());
		            System.out.println("메뉴를 결정했습니다.");
		            scan.nextLine();
		            return;
		        }
		    }
		}
	}
	
	private static void categoryRandom() {
		Main.printMenu("카테고리 선택");
		Main.printOption("한식", "양식", "일식", "중식", "기타");

		String select = scan.nextLine();
		rndCategoryList(select);
	}

	private static void rndCategoryList(String select) {
		String result = "";
		result = select;

		if (result.equals("5")) {
			result = "0";
		}

		List<Menu> categoryList = new ArrayList<>();

		for (Menu categoryMenu : MenuData.list) {
			if (result.equals(String.valueOf(categoryMenu.getCategoryNo()))) {
				categoryList.add(categoryMenu);
			}
		}

		// 랜덤하게 메뉴를 선택하여 출력
		Random random = new Random();

		// 카테고리 메뉴 추천
		while (true) {
			List<Menu> rndCategoryMenuList = new ArrayList<>();
			
			int i = 0;

			for (int count = 0; count < 5; count++) {
				i++;
				int randomCategoryMenuIndex = random.nextInt(categoryList.size());
				Menu rndCategoryMenu = categoryList.get(randomCategoryMenuIndex);
				System.out.printf("%d. " + rndCategoryMenu.getName() + "\n", i);

				rndCategoryMenuList.add(rndCategoryMenu);
			}

			System.out.println("6. 새로운 메뉴 추천");
			System.out.println("0. 돌아가기");
			Main.printLine();
			System.out.print("번호 입력: ");
			String input = scan.nextLine();

			if (input.equals("0")) {
				return; // 0을 입력한 경우 다른 동작을 수행하거나 메소드를 호출할 수 있음
			} else {
				if (Integer.parseInt(input) > 0 && Integer.parseInt(input) < rndCategoryMenuList.size() + 1) {
					int selectedMenuIndex = Integer.parseInt(input) - 1;
					Menu selectedMenu = rndCategoryMenuList.get(selectedMenuIndex);
					String todayDate = new SimpleDateFormat("yyMMdd").format(new Date());
					MenuChoose newMenuChoose = new MenuChoose(String.valueOf(MenuChooseData.list.size() + 1), Login.user.getNo(), selectedMenu.getNo(), todayDate);
					MenuChooseData.list.add(newMenuChoose);
					MenuChooseData.save();
					
					System.out.println("선택한 메뉴: " + selectedMenu.getName());
					System.out.println("메뉴를 결정했습니다.");
					scan.nextLine();
					return;
				}
			}
		}
	}

	private static void randomMenu() {
		Random random = new Random();
		int rndmenu = random.nextInt(MenuData.list.size());

		System.out.println();
		System.out.println(MenuData.list.get(rndmenu).getName());
		System.out.println();

		Main.printLine();
		Main.printOption("다른 메뉴 보기", "메뉴 결정");
		String input = scan.nextLine();

		if (input.equals("1")) {
			randomMenu();
		} else if (input.equals("2")) {
			String todayDate = new SimpleDateFormat("yyMMdd").format(new Date());
			MenuChoose newMenuChoose = new MenuChoose(String.valueOf(MenuChooseData.list.size() + 1), Login.user.getNo(), MenuData.list.get(rndmenu).getNo(), todayDate);
			MenuChooseData.list.add(newMenuChoose);
			MenuChooseData.save();
			
			System.out.println("선택한 메뉴: " + MenuData.list.get(rndmenu).getName());
			System.out.println("메뉴를 결정했습니다.");
			scan.nextLine();
		} else if (input.equals("0")) {
			return;
		} else {
			System.out.println("잘못된 번호를 입력하셨습니다.");
		}
	}
}