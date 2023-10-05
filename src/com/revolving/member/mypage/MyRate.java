package com.revolving.member.mypage;

import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

import com.revolving.Main;
import com.revolving.data.MemberData;
import com.revolving.data.RatingData;
import com.revolving.data.StoreData;
import com.revolving.data.object.Member;
import com.revolving.data.object.Rating;
import com.revolving.data.object.Store;
import com.revolving.login.Login;

/**
 * 나의 매장 평가 클래스
 * 목적: 나의 매장 평가에 대한 조회, 추가, 수정, 삭제 기능을 수행한다.
 * 기능:
 * - 나의 매장 평가 조회: 나의 매장 평가를 조회한다.
 * - 나의 매장 평가 추가: 매장 번호, 리뷰 내용, 평점을 입력하여 평가를 추가한다.
 * - 나의 매장 평가 수정: 나의 매장 평가를 수정한다.
 * - 나의 매장 평가 삭제: 나의 매장 평가를 삭제한다.
 * 
 * @author 이승원
 */
public class MyRate {

	static Scanner scan = new Scanner(System.in);

	/**
     * 나의 매장 평가 기능을 선택하는 메서드
     */
	public static void viewUserRate() {

		while (true) {
			Main.printMenu("나의 매장 평가");
			Main.printOption("나의 매장 평가 조회", "나의 매장 평가 추가", "나의 매장 평가 수정", "나의 매장 평가 삭제");
			String input = scan.nextLine().trim();

			if (input.equals("1")) {
				Main.printMenu("나의 매장 평가 조회");
				viewMyStoreReview();

			} else if (input.equals("2")) {
				Main.printMenu("나의 매장 평가 추가");
				addReview();

			} else if (input.equals("3")) {
				Main.printMenu("나의 매장 평가 수정");
				viewMyStoreReview();
				editReview();

			} else if (input.equals("4")) {
				Main.printMenu("나의 매장 평가 삭제");
				viewMyStoreReview();
				deleteReview();
			} else {
				return;
			}
			System.out.println("Enter를 누르면 이전 화면으로 돌아갑니다.");
			scan.nextLine();
		}
	}

	/**
     * 나의 매장 평가를 조회하는 메서드
     */
	private static void viewMyStoreReview() {
		int count = 0;
		int endIndex = 0;

		// 사용자 리뷰 개수 계산
		for (Member member : MemberData.list) {
			if (member.getId().equals(Login.user.getId())) {
				for (Rating review : RatingData.list) {
					if (review.getMemberNo().equals(member.getNo())) {
						count++;
					}
				}
			}
		}
		endIndex = count;
		count = 0;

		Collections.sort(RatingData.list,
				(review1, review2) -> Integer.parseInt(review1.getNo()) - Integer.parseInt(review2.getNo()));

		for (Member member : MemberData.list) {
			if (member.getId().equals(Login.user.getId())) {
				for (Rating review : RatingData.list) {
					if (review.getMemberNo().equals(member.getNo())) {
						count++;

						System.out.printf("리뷰번호: %s\n", review.getNo());

						for (Store store : StoreData.list) {
							if (store.getNo().equals(review.getStoreNo())) {
								System.out.printf("매장명: %s\n", store.getName());
								System.out.printf("매장주소: %s\n", store.getAddress());
								System.out.printf("전화번호: %s\n", store.getTel());
								break;
							}
						}

						System.out.printf("리뷰: %s\n", review.getReview());
						System.out.printf("평점: %s\n", review.getScore());

						if (count != endIndex) {
							System.out.println();
						}
					}
				}

				Main.printLine();
				if (count == 0) {
					System.out.println("작성한 리뷰가 없습니다.");
				}
			}
		}
	}

	/**
     * 나의 매장 평가를 추가하는 메서드
     */
	private static void addReview() {
		System.out.printf("1 ~ %d의 매장 번호를 입력하세요.\n", StoreData.list.size());
		System.out.print("매장 번호 입력: ");
		String storeNo = scan.nextLine().trim();
		if (Integer.parseInt(storeNo) < 1 || (Integer.parseInt(storeNo) > StoreData.list.size())) {
			System.out.println("잘못된 매장 번호를 입력하셨습니다.");
			return;
		}

		System.out.print("리뷰 내용 입력: ");
		String review = scan.nextLine().trim();

		System.out.print("0 ~ 5의 평점을 입력하세요.\n");
		System.out.print("평점 입력: ");
		String score = scan.nextLine().trim();
		if (Integer.parseInt(score) < 0 || Integer.parseInt(score) > 5) {
			System.out.println("잘못된 평점을 입력하셨습니다.");
			return;
		}

		Rating newReview = new Rating(String.valueOf(RatingData.list.size() + 1), Login.user.getNo(), storeNo, review,
				score);
		RatingData.list.add(newReview);
		RatingData.save();
		System.out.println("리뷰를 작성했습니다.");
	}

	/**
     * 나의 매장 평가를 수정하는 메서드
     */
	private static void editReview() {
		System.out.print("수정할 리뷰 번호 입력: ");
		String reviewNo = scan.nextLine().trim();

		for (Rating review : RatingData.list) {
			if (review.getNo().equals(reviewNo) && review.getMemberNo().equals(Login.user.getNo())) {
				System.out.print("새로운 리뷰 입력: ");
				String newReview = scan.nextLine().trim();

				System.out.print("새로운 평점 입력: ");
				String newScore = scan.nextLine().trim();
				if (Integer.parseInt(newScore) < 0 || Integer.parseInt(newScore) > 5) {
					System.out.println("잘못된 평점을 입력하셨습니다.");
					return;
				}

				review.setReview(newReview);
				review.setScore(newScore);

				RatingData.save();
				System.out.println("리뷰를 수정했습니다.");
				return;
			}
		}

		System.out.println("잘못된 리뷰 번호를 입력하셨습니다.");
	}

	/**
     * 나의 매장 평가를 삭제하는 메서드
     */
	private static void deleteReview() {
		System.out.print("삭제할 리뷰 번호 입력: ");
		String reviewNo = scan.nextLine().trim();

		Iterator<Rating> iterator = RatingData.list.iterator();

		while (iterator.hasNext()) {
			Rating review = iterator.next();
			if (review.getNo().equals(reviewNo) && review.getMemberNo().equals(Login.user.getNo())) {
				iterator.remove();
				RatingData.save();
				System.out.println("리뷰를 삭제했습니다.");
				return;
			}
		}

		System.out.println("잘못된 리뷰 번호를 입력하셨습니다.");
	}
	
	/**
	 * 나의 매장 평가를 삭제하는 메서드
	 * 
	 * @param reviewNo 삭제할 리뷰 번호
	 */
	public static void deleteReview(String reviewNo) {
		Iterator<Rating> iterator = RatingData.list.iterator();

		while (iterator.hasNext()) {
			Rating review = iterator.next();
			if (review.getNo().equals(reviewNo) && review.getMemberNo().equals(Login.user.getNo())) {
				iterator.remove();
				RatingData.save();
				System.out.println("리뷰를 삭제했습니다.");
				return;
			}
		}

		System.out.println("잘못된 리뷰 번호를 입력하셨습니다.");
	}
}
