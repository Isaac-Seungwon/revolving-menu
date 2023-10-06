package com.revolving.admin;
import java.util.*;
import com.revolving.Main;
import com.revolving.data.CategoryData;
import com.revolving.data.object.Category;

public class CategoryAdd {
	/**
	 * 카테고리 추가 클래스
	 * 
	 * @author lje 
	 * 목적 : 새로운 카테고리를 추가한다. 기능 : 원하는 카테고리의 번호와 이름을 추가한다. 중복은 불가능하다.
	 * 
	 */

	static void Addcategory() {
		Scanner sc = new Scanner(System.in);
		Category addcategory = new Category(null, null);

		Main.printMenu("카테고리 추가하기");
		System.out.println("현재 카테고리는 다음과 같습니다.");
		System.out.println("[번호] [카테고리명]");
		for (Category c : CategoryData.list) {
			System.out.printf("   %s\t   %s\n", c.getNo(), c.getCookery());
		}

		boolean loop = true;
		while (loop) {
			System.out.println("추가하실 카테고리의 번호와 이름을 입력하십시오.");
			System.out.print("번호 : ");
			String num = sc.nextLine().trim();
			System.out.print("이름 : ");
			String name = sc.nextLine().trim();

			if (num.matches("\\d+") && name.matches("[a-zA-Z가-힣]+")) {
				if(isCheck(num, name)) {
					System.out.println("번호 또는 이름이 이미 존재하고 있습니다.");
					continue;
				}else {
						addcategory.setNo(num);
						addcategory.setCookery(name);

						CategoryData.list.add(addcategory);
						CategoryData.save();
						System.out.println("카테고리가 추가되었습니다.");
						System.out.println("관리자 메뉴로 이동하시기 위해 Enter를 입력해 주십시오.");
						sc.nextLine();
						AdminMenu.menu();
						loop=false;
				}

			} else
				System.out.println("번호는 숫자만, 이름은 문자만 입력해 주시기 바랍니다.");
		}
	}
	
	public static boolean isCheck(String num, String name) {
		for(Category c : CategoryData.list) { 
			if(c.getNo().equals(num) || c.getCookery().equals(name)) 
				return true;
		}
		return false;
	}

}
