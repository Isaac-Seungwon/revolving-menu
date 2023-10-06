package com.revolving.admin.store;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import com.revolving.Main;
import com.revolving.data.MenuData;
import com.revolving.data.StoreData;
import com.revolving.data.object.Menu;
import com.revolving.data.object.Store;

public class AdminStoreSort {
	
	static Scanner scan;
	static private int currentIndex;
	static private ArrayList<Store> list;
	
	static {
		list = new ArrayList<Store>();
		scan = new Scanner(System.in);
	}
	
	public void sort() {
		
		while (true) {
			
			currentIndex = 0;
			
			Main.printMenu("매장 조회");
			Main.printOption("전체 조회", "단어 검색");
			
			String input = scan.nextLine().trim();
			
			if (input.equals("1")) {
				allSort();
			} else if (input.equals("2")) {
				nameSort();
			} else if (input.equals("0")) {
				return;
			} else {
				System.out.println("잘못 입력하셨습니다.");
			}
			
		}
		
	}

	private void nameSort() {

		Main.printMenu("매장 조회 > 이름 검색");
		System.out.print("단어 입력: ");
		String input = scan.nextLine().trim();
		
		for (Store store : StoreData.list) {
			if (store.getName().contains(input)) {
				list.add(store);
			}
		}
		
		while (true) {
			
			int endIndex = Math.min(currentIndex + 20, list.size());
			
			Main.printMenu(input + " 검색");
			
			sortResort(list, endIndex);
			
			if (!choiceStore(list, endIndex)) {
				list.clear();
				return;
			}
			
		}
		
	}

	private void allSort() {
		
		for (Store store : StoreData.list) {
			if(store.getName().equals("-")) {
				continue;
			}
			list.add(store);
		}
		
		while (true) {
			int endIndex = Math.min(currentIndex + 20, list.size());
			
			Main.printMenu("매장 조회 > 전체 조회");
			
			Collections.sort(list, (store1, store2) -> store1.getName().compareTo(store2.getName()));
			
			sortResort(list, endIndex);
			
			if(!choiceStore(list, endIndex)) {
				list.clear();
				return;
			}
		}
	
	}
	
	private void sortResort(ArrayList<Store> list, int endIndex) {
		
		for (int i = currentIndex; i < endIndex; i++) {
			
			Store store = list.get(i);
			
			System.out.printf("매장번호: %s\r\n", store.getNo());
			System.out.printf("매장명: %s\r\n", store.getName());
			for (Menu menu : MenuData.list) {
				if (menu.getNo().equals(store.getMenuNo())) {
					System.out.printf("대표메뉴: %s\r\n", menu.getName());
				}
				break;
			}
			System.out.printf("매장주소: %s\r\n", store.getAddress());
			System.out.printf("전화번호: %s\r\n", store.getTel());
			
			if (i != endIndex - 1) {
				System.out.println();
			}
			
		}
		
	}

	private boolean choiceStore(ArrayList<Store> list, int endIndex) {
		
        while (true) {
            Main.printLine();
            if (endIndex < list.size()) {
                System.out.println("1. 다음 매장 보기");
            }
            if (currentIndex > 0) {
                System.out.println("2. 이전 매장 보기");
            }
            System.out.println("3. 매장 수정 / 삭제");
            System.out.println("0. 돌아가기");
            System.out.print("번호 입력: ");
            String input = scan.nextLine().trim();

            if (input.equals("1")) {
                if (endIndex < list.size()) {
                    currentIndex += 20;
                    return true;
                } else {
                    System.out.println("다음 매장이 없습니다.");
                    scan.nextLine();
                }
            } else if (input.equals("2")) {
                if (currentIndex > 0) {
                    currentIndex = Math.max(0, currentIndex - 20);
                    return true;
                } else {
                    System.out.println("이전 매장이 없습니다.");
                    scan.nextLine();
                }
            } else if (input.equals("3")) {
            	Main.printLine();
            	System.out.print("매장 번호 입력: ");
            	input = scan.nextLine().trim();
            	
            	if (input.matches("\\d+") && Integer.parseInt(input) > 0 && Integer.parseInt(input) <= StoreData.list.size()) {
            		AdminStoreChange adminStoreChange = new AdminStoreChange();
                	adminStoreChange.change(input);
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