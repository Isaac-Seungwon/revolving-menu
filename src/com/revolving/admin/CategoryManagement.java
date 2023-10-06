 	package com.revolving.admin;

import com.revolving.Main;
import com.revolving.data.CategoryData;
import com.revolving.data.object.Category;

import java.io.BufferedReader;
import java.util.*;

public class CategoryManagement {
	/**
	 * 카테고리 관리 클래스
	 * 
	 * @author lje 
	 * 목적 : 관리자가 카테고리를 관리하는 클래스 기능 : 카테고리 추가, 삭제가 가능하다.
	 */

	public static void checkCategory() {
		Scanner sc = new Scanner(System.in);

		while (true) {
			Main.printMenu("카테고리 관리");
			Main.printLine();
			Main.printOption("카테고리 추가", "카테고리 삭제");
			String check = sc.nextLine().trim();

			if (check.equals("1")) { // 추가
				CategoryAdd.Addcategory();
			} else if (check.equals("2")) { // 삭제
				CategoryDelete.categoryDel();
			} else if(check.equals("0")) {
				AdminMenu.Menu();  //관리자 메뉴로 이동.
			}else {
				System.out.println("올바른 번호를 입력해주시기 바랍니다.");
				System.out.println("계속하시려면 Enter를 입력해 주십시오.");
				sc.nextLine();
			}		
		}
	}

}

