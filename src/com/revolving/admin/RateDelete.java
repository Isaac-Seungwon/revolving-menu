package com.revolving.admin;
import java.util.*;

import com.revolving.Main;
import com.revolving.data.MemberData;
import com.revolving.data.RatingData;
import com.revolving.data.StoreData;
import com.revolving.data.object.Member;
import com.revolving.data.object.Rating;
import com.revolving.data.object.Store;

public class RateDelete {

	public static void RateDel() {
		/**
		 * 사용자 후기 삭제 클래스
		 * 
		 * @author lje 목적 : 사용자의 후기를 선택 후 삭제하는 클래스
		 * 
		 */
		Scanner sc = new Scanner(System.in);
		ArrayList<Rating> delToReview = new ArrayList<Rating>();
		Main.printMenu("사용자 후기 삭제");

		System.out.println("어떤 회원의 후기를 삭제하시겠습니까?");

		System.out.print("회원 번호 : ");
		String num = sc.nextLine().trim();

		for (Rating r : RatingData.list) {
			if (r.getMemberNo().equals(num)) {
				delToReview.add(r); // 회원번호가 일치할 경우, 회원이 작성한 후기를 담는 리스트
			}
		}
		
		boolean flag=true;
		
		while (flag) {
			if (!delToReview.isEmpty()) {  //리스트가 비워져있지 않다면
				Main.printLine();
				System.out.printf("회원번호 : %s\n", num);
				System.out.println("[후기ID]\t\t[매장명]\t\t\t\t\t[매장 후기]\t\t\t[평점]");
				for (Store s : StoreData.list) {
					for (Rating r : delToReview) {
						if (s.getNo().equals(r.getStoreNo())) {
							System.out.printf("    %s\t\t%s\t\t\t\t%s\t\t\t%s\n", r.getNo(), s.getName(), r.getReview(),
									r.getScore());
						}
					}
				} //특정 회원이 쓴 후기들을 출력

				System.out.println("삭제할 후기 ID를 입력해 주시기 바랍니다.");
				System.out.print("후기 ID : ");
				String delID = sc.nextLine().trim();

				Rating realDeleteReview = null;
				for (Rating r : delToReview) {  //회원이 작성한 후기들을 for문으로 돌리면서
					if (r.getNo().equals(delID)) {  //삭제할 후기ID와 같은 숫자가 있을 경우
						realDeleteReview = r; //새로운 rating에 담기
					}
				}

				if (realDeleteReview != null) {
					Main.printLine();
					System.out.println("정말로 삭제하시겠습니까..?");
					System.out.print("삭제를 원하시면 Y를, 원하지 않으시면 N을 입력해 주십시오 : ");
					String answer = sc.nextLine().trim();

					if (answer.toUpperCase().equals("Y")) {
						RatingData.list.remove(realDeleteReview);
						RatingData.save();
						System.out.println("삭제가 정상적으로 완료되었습니다. Enter를 입력하시면 관리자 메뉴로 돌아갑니다.");
						sc.nextLine();
						AdminMenu.menu();
					} else if (answer.toUpperCase().equals("N")) {
						System.out.println("삭제가 취소되었습니다. Enter를 입력하시면 관리자 메뉴로 돌아갑니다.");
						sc.nextLine();
						AdminMenu.menu();
						flag=false;
					} else
						System.out.println("바르게 입력해 주시기 바랍니다.");
				} else
					System.out.println("일치하는 후기 ID를 찾을 수 없습니다. 다시 입력해 주시기 바랍니다.");
			} else
				System.out.println("해당 회원의 후기를 찾을 수 없습니다. 회원번호를 다시 입력해 주세요.");
		}
	}

}
