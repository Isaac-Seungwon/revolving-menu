package com.revolving.admin;

import java.util.*;
import com.revolving.Main;
import com.revolving.data.MemberData;
import com.revolving.data.RatingData;
import com.revolving.data.StoreData;
import com.revolving.data.object.Member;
import com.revolving.data.object.Rating;
import com.revolving.data.object.Store;

public class RateView {
	/**
	 * 
	 * 사용자 후기 조회 클래스
	 * 
	 * @author lje 목적 : 사용자가 입력한 후기들을 정렬기준에 따라 조회하는 클래스 기능 : 정렬기준은 별점 낮은순, 별점 높은순,
	 *         매장명 검색, 회원 번호 검색 중에 선택하여 조회할 수 있다.
	 */

	private static int currentIndex; // 50개씩 보기
	static Scanner sc = new Scanner(System.in);

	public static void SeeReview() {
		ArrayList<Store> newStore = new ArrayList<Store>();
		ArrayList<Rating> newMember = new ArrayList<Rating>();

		Main.printMenu("사용자 후기 조회");

		while (true) {
			Main.printOption("별점 낮은 순", "별점 높은 순", "매장명 검색", "회원 번호 검색");
			String num = sc.nextLine().trim();

			if (num.equals("1")) {
				Main.printLine();
				Collections.sort(RatingData.list,
						(a, b) -> Integer.compare(Integer.parseInt(a.getScore()), Integer.parseInt(b.getScore())));
			} else if (num.equals("2")) {
				Main.printLine();
				Collections.sort(RatingData.list,
						(a, b) -> Integer.compare(Integer.parseInt(b.getScore()), Integer.parseInt(a.getScore())));
			} else if (num.equals("3")) {
				while (true) {
					newStore.clear();
					Main.printLine();
					System.out.println("후기를 확인하고 싶은 매장의 이름을 입력하십시오.");
					System.out.print("매장명 : ");
					String storeName = sc.nextLine().trim();

					for (Store s : StoreData.list) {
						if (s.getName().replace(" ", "").contains(storeName.replace(" ", ""))) {
							newStore.add(s);
						}
					}
					if (newStore.isEmpty()) {
						System.out.println("입력하신 매장과 일치하는 매장이 없습니다. 다시 검색해 주시기 바랍니다.");
						continue;
					} else
						break;
				}
			} else if (num.equals("4")) {
				while (true) {
					newMember.clear();
					Main.printLine();
					System.out.println("후기를 확인하고 싶은 회원의 번호를 입력하십시오.");
					System.out.print("회원 번호 : ");
					String memberNum = sc.nextLine().trim();

					for (Rating r : RatingData.list) {
						if (r.getMemberNo().equals(memberNum)) {
							newMember.add(r);
						}
					}

					if (newMember.isEmpty()) {
						System.out.println("입력하신 번호와 일치하는 회원이 없습니다. 다시 검색해 주시기 바랍니다.");
						continue;
					} else
						break;

				}
			} else if (num.equals("0")) {
				AdminMenu.menu(); // 관리자 메뉴로 이동
			} else {
				System.out.println("올바른 번호를 입력해주시기 바랍니다.");
				System.out.println("계속하시려면 Enter를 입력해 주십시오.");
				sc.nextLine();
			}

			System.out.println("[회원번호]\t\t[매장명]\t\t\t\t\t[매장 후기]\t\t\t[평점]");

			if (num.equals("1") || num.equals("2")) {
				currentIndex=0;
				
				while (true) {
					// 표시할 리뷰의 끝 인덱스 계산
					int endIndex = Math.min(currentIndex + 30, RatingData.list.size());
					Rating reviewPage=null;
					for (int i = currentIndex; i < endIndex; i++) {
						reviewPage = RatingData.list.get(i);

						for (Member m : MemberData.list) {
							for (Store s : StoreData.list) {
								if (m.getNo().equals(reviewPage.getMemberNo())
										&& s.getNo().equals(reviewPage.getStoreNo())) {
									System.out.printf("    %s\t\t%s\t\t\t\t%s\t\t\t\t%s\n", m.getNo(), s.getName(),
											reviewPage.getReview(), reviewPage.getScore());
								}
							}
						}
						
					}
					if (!choiceStoreReview(endIndex)) {
						return;
					}
				}

			} else if (num.equals("3")) {
				for (Store s : newStore) {
					for (Rating r : RatingData.list) {
						for (Member m : MemberData.list) {
							if (m.getNo().equals(r.getMemberNo()) && s.getNo().equals(r.getStoreNo())) {
								System.out.printf("    %s\t\t%s\t\t\t\t%s\t\t\t\t%s\n", m.getNo(), s.getName(),
										r.getReview(), r.getScore());
							}
						}
					}
				}
			} else if (num.equals("4")) {
				for (Store s : StoreData.list) {
					for (Rating m : newMember) {
						if (s.getNo().equals(m.getStoreNo())) {
							System.out.printf("    %s\t\t%s\t\t\t\t%s\t\t\t%s\n", m.getMemberNo(), s.getName(),
									m.getReview(), m.getScore());
						}
					}
				}
			}

			Main.printLine();
		}
	}

	private static boolean choiceStoreReview(int endIndex) {
		while (true) {
			Main.printLine();
			if (endIndex < RatingData.list.size()) {
				System.out.println("1. 다음 리뷰 보기");
			}
			if (currentIndex > 0) {
				System.out.println("2. 이전 리뷰 보기");
			}
			System.out.println("0. 돌아가기");
			System.out.print("번호 입력: ");
			String input = sc.nextLine().trim();

			if (input.equals("1")) {
				if (endIndex < RatingData.list.size()) {
					currentIndex += 30;
					return true;
				} else {
					System.out.println("다음 리뷰가 없습니다.");
					sc.nextLine();
				}
			} else if (input.equals("2")) {
				if (currentIndex > 0) {
					currentIndex = Math.max(0, currentIndex - 30);
					return true;
				} else {
					System.out.println("이전 리뷰가 없습니다.");
					sc.nextLine();
				}
			} else if (input.equals("0")) {
				System.out.println("Enter를 누르면 이전 화면으로 돌아갑니다.");
				sc.nextLine();
				return false;
			} else {
				System.out.println("잘못 입력하셨습니다.");
			}
		}
	}

}
