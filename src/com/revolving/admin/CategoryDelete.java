package com.revolving.admin;

import java.util.*;
import com.revolving.Main;
import com.revolving.data.CategoryData;
import com.revolving.data.MenuData;
import com.revolving.data.object.Category;
import com.revolving.data.object.Menu;

public class CategoryDelete {

	public static void categoryDel() { // 카테고리 삭제
		Scanner sc = new Scanner(System.in);

		Main.printMenu("카테고리 삭제하기");
		System.out.println("[번호] [카테고리명]");
		for (Category c : CategoryData.list) {
			System.out.printf("   %s\t   %s\n", c.getNo(), c.getCookery());
		}

		boolean flag = true;
		while (flag) {
			System.out.println("삭제할 카테고리의 번호를 입력하세요. ");
			System.out.print("입력 : ");
			String del = sc.nextLine().trim();

			Category delToCategory = null;
			for (Category c : CategoryData.list) {
				if (del.equals(c.getNo())) {
					delToCategory = c;
				}
			}

			if (delToCategory != null) {
				Main.printLine();
				System.out.println("정말로 삭제하시겠습니까..?");
				System.out.print("삭제를 원하시면 Y를, 원하지 않으시면 N을 입력해 주십시오 : ");
				String answer = sc.nextLine().trim();

				if (answer.toUpperCase().equals("Y")) {
					int delIndex=CategoryData.list.indexOf(delToCategory);
					
					CategoryData.list.remove(delToCategory);
					System.out.println("카테고리가 정상적으로 삭제되었습니다. Enter를 입력하시면 관리자 메뉴로 돌아갑니다.");

					int i = 0;
					for (Category reCategory : CategoryData.list) {
						reCategory.setNo(String.valueOf(i)); // 카테고리 순서 재정의
						i++;
					}
					
					for(Menu m : MenuData.list) {  //삭제된 카테고리의 메뉴들을 기타로 재설정
						if(m.getCategoryNo().equals(del)) {
							m.setCategoryNo(String.valueOf('0'));
						}
					}

					for(Menu m : MenuData.list) { // 카테고리 삭제되고 번호 땡겨지면 메뉴 카테고리 번호도 수정
						if (Integer.parseInt(m.getCategoryNo()) > delIndex) {
	                        int newCategoryNo = Integer.parseInt(m.getCategoryNo()) - 1;
	                        m.setCategoryNo(String.valueOf(newCategoryNo));
	                    }
					}
					
					MenuData.save();
					CategoryData.save();
					sc.nextLine();
					AdminMenu.menu();
				} else if (answer.toUpperCase().equals("N")) {
					System.out.println("삭제가 취소되었습니다. Enter를 입력하시면 관리자 메뉴로 돌아갑니다.");
					sc.nextLine();
					AdminMenu.menu();
					flag = false;
				} else
					System.out.println("바르게 입력해 주시기 바랍니다.");

			} else
				System.out.println("일치하는 번호가 없습니다. 다시 입력해 주세요.");

		}

	}
}