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
	 * @author lje 목적 : 관리자가 카테고리를 관리하는 클래스 기능 : 카테고리 추가, 수정, 삭제가 가능하다.
	 */

	public static void checkCategory() {
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			Main.printMenu("카테고리 관리");
			Main.printLine();
			Main.printOption("카테고리 추가", "카테고리 수정", "카테고리 삭제");
			String check = sc.nextLine().trim();

			if (check.equals("1")) {  //추가

			} else if (check.equals("2")) {  //수정

			} else if (check.equals("3")) {  //삭제
				Main.printMenu("카테고리 삭제");
				System.out.println("삭제할 카테고리의 번호 또는 이름을 입력하세요. ");
				String del=sc.nextLine().trim();
				
				//ArrayList<Category> deleteCategory = new ArrayList<Category>();
				
				for(Category c : CategoryData.list) {
					if(del.equals(c)) {
						CategoryData.list.remove(c);
					}
				}
				
				CategoryData.save();
				
				
				
				
			} else
				return;
		}

	}

}
