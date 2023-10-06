package com.revolving.member.mypage;

import java.util.Iterator;
import java.util.Scanner;

import com.revolving.Main;
import com.revolving.data.MemberData;
import com.revolving.data.MenuChooseData;
import com.revolving.data.MenuData;
import com.revolving.data.RatingData;
import com.revolving.data.StoreData;
import com.revolving.data.object.Member;
import com.revolving.data.object.Menu;
import com.revolving.data.object.MenuChoose;
import com.revolving.data.object.Rating;
import com.revolving.data.object.Store;
import com.revolving.login.Login;

/**
 * 나의 추천 메뉴 클래스
 * 목적: 나의 추천 메뉴에 대한 조회, 삭제 기능을 수행한다.
 * 기능:
 * - 나의 추천 메뉴 조회: 메뉴와 방문일을 출력한다.
 * - 나의 추천 메뉴 삭제: 넘버링된 번호를 입력하여 메뉴 기록을 삭제한다.
 * 
 * @author 이승원
 */
public class MyStoreList {

	static Scanner scan = new Scanner(System.in);

	/**
	 * 나의 추천 메뉴를 조회 및 삭제하는 메서드
	 */
	public static void viewMyStoreList() {

		while (true) {
			Main.printMenu("나의 추천 메뉴");
			Main.printOption("나의 추천 메뉴 조회", "나의 추천 메뉴 삭제");
			String input = scan.nextLine().trim();

			if (input.equals("1")) {

				Main.printMenu("나의 추천 메뉴 조회");

				for (Member member : MemberData.list) {
					if (member.getId().equals(Login.user.getId())) {
						viewMyMenuRecord(member);
					}
				}
				System.out.println("Enter를 누르면 이전 화면으로 돌아갑니다.");
				scan.nextLine();

			} else if (input.equals("2")) {
				Main.printMenu("나의 추천 메뉴 삭제");
				
				for (Member member : MemberData.list) {
					if (member.getId().equals(Login.user.getId())) {
						viewMyMenuRecord(member);
						break;
					}
				}
				
				deleteVisitedStoreRecord();

			} else {
				return;
			}
		}
	}

	/**
     * 회원의 추천 메뉴 기록을 조회하는 메서드
     * 
     * @param member 로그인 회원
     */
	private static void viewMyMenuRecord(Member member) {
		int count = 1;
		
		for (MenuChoose menuChoose : MenuChooseData.list) {
			if (menuChoose.getMemberNo().equals(member.getNo())) {
				boolean storeFound = false;

				// 매장명이 있는 경우
				for (Menu menu : MenuData.list) {
					for (Store store : StoreData.list) {
						for (Rating review : RatingData.list) {
							if (store.getMenuNo().equals(menuChoose.getMenuNo())) {
								if (store.getNo().equals(review.getStoreNo())) {
									if (menu.getNo().equals(menuChoose.getMenuNo())) {
										System.out.printf("%d. 메뉴명: %s, 매장명: %s, 방문일: %s\n", count++, menu.getName(), store.getName(), menuChoose.getDate());
										storeFound = true;
										break;
									}
								}
							}
						}
					}
				}
				
				// 매장명이 없는 경우
				if (!storeFound) {
					for (Menu menu : MenuData.list) {
						if (menu.getNo().equals(menuChoose.getMenuNo())) {
							System.out.printf("%d. 메뉴명: %s, 방문일: %s\n", count++, menu.getName(), menuChoose.getDate());
							break;
						}
					}
	            }
			}
		}
	}

	/**
     * 회원의 추천 메뉴 기록을 삭제하는 메서드
     */
	private static void deleteVisitedStoreRecord() {

		System.out.print("삭제할 번호 입력: ");
		int choice = scan.nextInt();
		
		Main.printLine();

		int count = 1;

		// 반복자로 추천 메뉴 기록을 하나씩 가져옴
		for (Iterator<MenuChoose> iterator = MenuChooseData.list.iterator(); iterator.hasNext();) {
			MenuChoose menuChoose = iterator.next();

			// 로그인한 사용자의 추천 메뉴 기록인지 확인
			if (menuChoose.getMemberNo().equals(Login.user.getNo())) {
				for (Menu menu : MenuData.list) {
					if (menu.getNo().equals(menuChoose.getMenuNo())) {
						
						// 사용자가 선택한 번호와 리스트 번호와 일치하면 해당 추천 메뉴 기록 삭제
						if (count == choice) {
                            iterator.remove();
                            MenuChooseData.save();
							System.out.print("선택한 메뉴 기록을 삭제했습니다.\n");
							scan.nextLine();
							
							// 해당 메뉴 기록에 대한 리뷰가 있으면 삭제할지 선택
							for (Store store : StoreData.list) {
								for (Rating review : RatingData.list) {
									if (store.getMenuNo().equals(menuChoose.getMenuNo())) {
										if (store.getNo().equals(review.getStoreNo()) && review.getMemberNo().equals(Login.user.getNo())) {
											Main.printLine();
											System.out.printf("리뷰번호: %s\n", review.getNo());
											System.out.printf("매장명: %s\n", store.getName());
											System.out.printf("매장주소: %s\n", store.getAddress());
											System.out.printf("매장번호: %s\n", store.getTel());
											System.out.printf("리뷰: %s\n", review.getReview());
											System.out.printf("평점: %s\n", review.getScore());
											Main.printLine();
											System.out.print("삭제한 메뉴에 대한 매장 리뷰를 작성한 기록이 있습니다.\n");
											System.out.print("해당 리뷰도 함께 삭제하시겠습니까? (y/n): ");
					                        String deleteChoice = scan.nextLine().trim();
					                        
					                        if (deleteChoice.equals("y")) {
												if (store.getNo().equals(review.getStoreNo())) {
													MyRate.deleteReview(review.getNo());
												}
					                        } else {
					                            System.out.println("해당 리뷰를 유지합니다.");
					                        }
					                        break;
										}
									}
								}
							}
							
							System.out.println("Enter를 누르면 이전 화면으로 돌아갑니다.");
							scan.nextLine();
							return;
						}
						count++;
					}
				}
			}
		}

		System.out.println("잘못된 메뉴 기록을 입력하셨습니다.");
		System.out.println("Enter를 누르면 이전 화면으로 돌아갑니다.");
		scan.nextLine();
		scan.nextLine();
		return;
	}
}
