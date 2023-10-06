package com.revolving.admin;

import java.util.Scanner;

import com.revolving.Main;
import com.revolving.admin.store.AdminStoreAdd;
import com.revolving.admin.store.AdminStoreSort;

public class StoreManagement {

	static Scanner scan = new Scanner(System.in);

	static public void store() {

		while (true) {

			Main.printMenu("매장 관리");
			Main.printOption("매장 조회", "매장 추가");

			String input = scan.nextLine().trim();

			if (input.equals("1")) {
				AdminStoreSort adminStoreSort = new AdminStoreSort();
				adminStoreSort.sort();
			} else if (input.equals("2")) {
				AdminStoreAdd adminStoreAdd = new AdminStoreAdd();
				adminStoreAdd.add();
			} else {
				return; // AdminMenu.menu
			}

		}

	}

}