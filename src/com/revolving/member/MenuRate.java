package com.revolving.member;

import java.util.Scanner;
import java.util.Collections;

import com.revolving.Main;
import com.revolving.data.MemberData;
import com.revolving.data.RatingData;
import com.revolving.data.StoreData;
import com.revolving.data.object.Member;
import com.revolving.data.object.Rating;
import com.revolving.data.object.Store;

/**
 * 메뉴 평가 조회 클래스
 * @author 이승원
 * 목적: 메뉴 평가를 조회한다.
 * 기능: 메뉴 평가 조회, 이전 리뷰 보기/다음 리뷰 보기 기능을 수행한다.
 */
public class MenuRate {

	static Scanner scan = new Scanner(System.in);
	private static int currentIndex = 0; // 현재 리뷰 인덱스 (1~20)

	/**
     * 매장 평가를 20개씩 조회하는 메서드.
     */
	public static void viewStoreReview() {

		while (true) {
			Main.printMenu("매장 평가 조회");

			// 매장명 기준 매장 리스트 정렬
			Collections.sort(StoreData.list, (store1, store2) -> store1.getName().compareTo(store2.getName()));

			// 표시할 리뷰의 끝 인덱스 계산
			int endIndex = Math.min(currentIndex + 20, RatingData.list.size());

			for (int i = currentIndex; i < endIndex; i++) {
				Rating review = RatingData.list.get(i);

				System.out.printf("리뷰번호: %s\n", review.getNo());

				for (Member member : MemberData.list) {
					if (member.getNo().equals(review.getMemberNo())) {
						System.out.printf("회원명: %s\n", member.getName());
						break;
					}
				}
				for (Store store : StoreData.list) {
					if (store.getNo().equals(review.getStoreNo())) {
						System.out.printf("매장명: %s\n", store.getName());
						System.out.printf("매장주소: %s\n", store.getAddress());
						System.out.printf("매장번호: %s\n", store.getTel());
						break;
					}
				}
				System.out.printf("리뷰: %s\n", review.getReview());
				System.out.printf("평점: %s\n", review.getScore());

				// 마지막 리뷰가 아닐 경우 개행
				if (i != endIndex - 1) {
					System.out.println();
				}
			}

			// 리뷰 선택 메뉴
			if (!choiceStoreReview(endIndex)) {
				return;
			}
		}
	}

	/**
     * 사용자가 다음 후기 보기, 이전 후기 보기를 선택할 수 있는 메서드.
     *
     * @param endIndex 표시할 리뷰의 끝 인덱스
     * @return false 반환
     */
	private static boolean choiceStoreReview(int endIndex) {
		while (true) {
			Main.printLine();
			if (endIndex < RatingData.list.size()) {
				System.out.println("1. 다음 후기 보기");
			}
			if (currentIndex > 0) {
				System.out.println("2. 이전 후기 보기");
			}
			System.out.println("0. 돌아가기");
			System.out.print("번호 입력: ");
			String input = scan.nextLine().trim();

			if (input.equals("1")) {
				if (endIndex < RatingData.list.size()) {
					currentIndex += 20;
					return true;
				} else {
					System.out.println("다음 후기가 없습니다.");
					scan.nextLine();
				}
			} else if (input.equals("2")) {
				if (currentIndex > 0) {
					currentIndex = Math.max(0, currentIndex - 20);
					return true;
				} else {
					System.out.println("이전 후기가 없습니다.");
					scan.nextLine();
				}
			} else if (input.equals("0")) {
				System.out.println("Enter를 누르면 이전 화면으로 돌아갑니다.");
				scan.nextLine();
				return false;
			} else {
				System.out.println("잘못 입력하셨습니다.");
			}
		}
	}
}