package com.revolving.member;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import com.revolving.Main;
import com.revolving.data.CategoryData;
import com.revolving.data.MenuData;
import com.revolving.data.object.Category;
import com.revolving.data.object.Menu;

public class MenuSort {
	
	static Scanner scan;
	static private int currentIndex;
	static private ArrayList<Menu> list;
	
	static {
		list = new ArrayList<Menu>();
		scan = new Scanner(System.in);
	}

	public void mesuSort() {

		while (true) {
			Main.printMenu("정렬 기준 선택");
			Main.printOption("전체 정렬", "카테고리별 정렬", "가격 순 정렬", "계절 별 정렬", "이름 검색");
			String input = scan.nextLine();
	
			if (input.equals("1")) {
				allSort();
			} else if (input.equals("2")) {
				categorySort();
			} else if (input.equals("3")) {
				Main.printMenu("메뉴 조회 > 가격 순 조회");
				System.out.println("1. 높은순");
				System.out.println("2. 낮은순");
				Main.printLine();
				System.out.print("번호 입력: ");
				
				input = scan.nextLine().trim();
				
				if(input.equals("1")) {
					priceSort("1");
				} else if (input.equals("2")) {
					priceSort("2");
				} else {
					System.out.println("잘못 입력하셨습니다.");
				}
			} else if (input.equals("4")) {
				sessonSort();
			} else if (input.equals("5")) {
				nameSort();
			} else if(input.equals("0")) {
				return;
			} else {
				System.out.println("잘못 입력하셨습니다.");
			}
		}
	}
	
	private void nameSort() {
		
		Main.printMenu("메뉴 조회 > 이름 검색");
		System.out.print("단어 입력: ");
		String input = scan.nextLine().trim();
		
		for (Menu menu : MenuData.list) {
			if (menu.getName().contains(input)) {
				list.add(menu);
			}
		}
		
		while (true) {
			
			int endIndex = Math.min(currentIndex + 20, list.size());
			
			Main.printMenu(input + " 검색");
			
			sortResort(list, endIndex);
			
			if(!choiceMenu(list, endIndex)) {
				list.clear();
				return;
			}
			
		}
	
}

private void sessonSort() {
	
	while (true) {
		
		Main.printMenu("메뉴 조회 > 계절별 조회");
		System.out.println("0. 기타");
		System.out.println("1. 여름");
		System.out.println("2. 겨울");
		Main.printLine();
		System.out.print("번호 입력: ");
		
		String input = scan.nextLine().trim();
		
		if (input.matches("\\d+") && Integer.parseInt(input) >= 0 && Integer.parseInt(input) < 3) {
			
			for (Menu menu : MenuData.list) {
				if(menu.getSeasonNo().equals(input)) {
					list.add(menu);
				}
			}
			
			while (true) {
				
				if (input.equals("0")) {
					Main.printMenu("기타 조회");
				} else if (input.equals("1")) {
					Main.printMenu("여름 조회");
				} else {
					Main.printMenu("겨울 조회");
				}
				
				int endIndex = Math.min(currentIndex + 20, list.size());
				
				sortResort(list, endIndex);
				
				if(!choiceMenu(list, endIndex)) {
					list.clear();
					return;
				}
				
			}
			
		} else {
			System.out.println("잘못 입력하셨습니다.");
		}
		
	}
	
}

private void priceSort(String string) {
	
	for (Menu menu : MenuData.list) {
		if (menu.getName().equals("-")) {
			continue;
		}
		list.add(menu);
	}

	while (true) {
		
		int endIndex = Math.min(currentIndex + 20, list.size());
		
		if (string.equals("1")) {
			Main.printMenu("높은순 조회");
			
			Collections.sort(list, Comparator.comparingInt(menu -> Integer.parseInt(((Menu) menu).getPrice())).reversed());
			
			sortResort(list, endIndex);
		} else {
			Main.printMenu("낮은순 조회");
			Collections.sort(list, Comparator.comparingInt(menu -> Integer.parseInt(menu.getPrice())));
			
			sortResort(list, endIndex);
		}
		
		if(!choiceMenu(list, endIndex)) {
			return;
		}
		
	}
	
}

private void categorySort() {
	
	while (true) {
		
		Main.printMenu("메뉴 조회 > 카테고리 별 조회");
		for(Category category : CategoryData.list) {
			System.out.printf("%s. %s\r\n", category.getNo(), category.getCookery());
		}
		Main.printLine();
		System.out.print("번호 입력: ");
		
		String input = scan.nextLine().trim();
		
		if (input.matches("\\d+") && Integer.parseInt(input) >= 0 && Integer.parseInt(input) < CategoryData.list.size()) {
			
			String cookery = "";
			
			for(Category category : CategoryData.list) {
				if(category.getNo().equals(input)) {
					cookery = category.getCookery();
					break;
				}
			}
			
			for (Menu menu : MenuData.list) {
				if (menu.getCategoryNo().equals(input)) {
					list.add(menu);
				}
			}
			
			while (true) {
				
				Main.printMenu(cookery + " 조회");
				
				int endIndex = Math.min(currentIndex + 20, list.size());
				
				sortResort(list, endIndex);
				
				if(!choiceMenu(list, endIndex)) {
					list.clear();
					return;
				}
				
			}
			
		} else {
			System.out.println("잘못 입력하셨습니다.");
		}
		
	}
	
}

private void allSort() {
	
		for (Menu menu : MenuData.list) {
			if (menu.getName().equals("-")) {
				continue;
			}
			
			list.add(menu);
		}
		
		while (true) {
			Main.printMenu("메뉴 조회 > 전체 조회");
			
			int endIndex = Math.min(currentIndex + 20, list.size());
			
			sortResort(list, endIndex);
			
			if(!choiceMenu(list, endIndex)) {
				list.clear();
				return;
			}
		}
	
}

private void sortResort(ArrayList<Menu> list, int endIndex) {
	
	for (int i = currentIndex; i < endIndex; i++) {
		
		Menu menu = list.get(i);
		
		System.out.printf("메뉴번호: %s\r\n", menu.getNo());
		System.out.printf("메뉴명: %s\r\n", menu.getName());
		for (Category category : CategoryData.list) {
			if (category.getNo().equals(menu.getCategoryNo())) {
				System.out.printf("카테고리: %s\r\n", category.getCookery());
				break;
			}
		}
		if (menu.getSeasonNo().equals("0")) {
			System.out.println("시즌: 기타");
		} else if (menu.getSeasonNo().equals("1")) {
			System.out.println("시즌: 여름");
		} else {
			System.out.println("시즌: 겨울");
		}
		System.out.printf("가격: %s원\r\n", menu.getPrice());
		
		if (i != endIndex - 1) {
			System.out.println();
		}
		
	}
	
}

private boolean choiceMenu(ArrayList<Menu> list, int endIndex) {
	
    while (true) {
        Main.printLine();
        if (endIndex < list.size()) {
            System.out.println("1. 다음 메뉴 보기");
        }
        if (currentIndex > 0) {
            System.out.println("2. 이전 메뉴 보기");
        }
        System.out.println("0. 돌아가기");
        System.out.print("번호 입력: ");
        String input = scan.nextLine().trim();

        if (input.equals("1")) {
            if (endIndex < list.size()) {
                currentIndex += 20;
                return true;
            } else {
                System.out.println("다음 메뉴가 없습니다.");
                scan.nextLine();
            }
        } else if (input.equals("2")) {
            if (currentIndex > 0) {
                currentIndex = Math.max(0, currentIndex - 20);
                return true;
            } else {
                System.out.println("이전 메뉴가 없습니다.");
                scan.nextLine();
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