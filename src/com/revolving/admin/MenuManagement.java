package com.revolving.admin;

import java.util.Scanner;

import com.revolving.Main;
import com.revolving.admin.menu.AdminMenuAdd;
import com.revolving.admin.menu.AdminMenuSort;

public class MenuManagement {
	
	static Scanner scan = new Scanner(System.in);
	
	static public void menu() {
		
		while(true) {
			
			Main.printMenu("메뉴 관리");
			Main.printOption("메뉴 조회", "메뉴 추가");
			
			String input = scan.nextLine().trim();
			
			if (input.equals("1")) {
				AdminMenuSort adminMenuSort = new AdminMenuSort();
				adminMenuSort.sort();
			} else if (input.equals("2")) {
				AdminMenuAdd adminMenuAdd = new AdminMenuAdd();
				adminMenuAdd.add();
			} else {
				return; // AdminMenu.menu
			}
			
		}
		
	}

}
