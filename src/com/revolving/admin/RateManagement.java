package com.revolving.admin;
import java.util.Scanner;
import com.revolving.Main;

public class RateManagement {
	/**
	 * 사용자의 평가 클래스
	 * @author lje
	 * 목적 : 사용자가 등록한 매장이나 음식에 대한 후기를 관리하는 클래스
	 * 기능 : 평가 조회 및 삭제가 가능하다.
	 */
	public static void checkRate(){
		Scanner sc = new Scanner(System.in);

		while (true) {
			Main.printMenu("사용자 후기 관리");
			Main.printLine();
			Main.printOption("후기 내역 조회", "후기 내역 삭제");
			String check = sc.nextLine().trim();

			if (check.equals("1")) { // 조회
				RateView.SeeReview();
			} else if (check.equals("2")) { // 삭제
				RateDelete.RateDel();
			} else if(check.equals("0")) {
				AdminMenu.menu();  //관리자 메뉴로 이동.
			}else {
				System.out.println("올바른 번호를 입력해주시기 바랍니다.");
				System.out.println("계속하시려면 Enter를 입력해 주십시오.");
				sc.nextLine();
			}		
		}
		
	}

}
