package com.revolving.member;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

import com.revolving.Main;
import com.revolving.data.MemberData;
import com.revolving.data.RatingData;
import com.revolving.data.StoreData;
import com.revolving.data.object.Member;
import com.revolving.data.object.Rating;
import com.revolving.data.object.Store;

public class MenuRate {

	static Scanner scan = new Scanner(System.in);
	private static int currentIndex = 0;
	
	public static void viewStoreReview() {
	
		Main.printMenu("매장 평가 열람");
		
		Collections.sort(StoreData.list, (store1, store2) -> store1.getName().compareTo(store2.getName()));

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
            System.out.printf("점수: %s\n", review.getScore());
            
            /*
            for (int j=1; j <= Integer.parseInt(review.getScore()); j++) {
            	System.out.print("★");
            }
            System.out.println();
            */
            
            if (i != 19 && (i + 1) % 20 != 0) {
            	System.out.println();
            }
        }

        
    	Main.printLine();
        if (endIndex < RatingData.list.size()) {
            System.out.println("1. 더 보기");
        }

        if (currentIndex > 0) {
            System.out.println("2. 이전 화면 보기");
        }
        System.out.print("번호 입력: ");
        String input = scan.nextLine().trim();
    
        
        
        
        
		if (input.equals("1")) {
			if (endIndex < RatingData.list.size()) {
                currentIndex += 20;
                viewStoreReview();
            } else {
                System.out.println("더 이상 리뷰가 없습니다.");
                System.out.println("Enter를 누르면 이전 화면으로 돌아갑니다.");
        		scan.nextLine();
            }
		} else if (input.equals("2")) {
			if (currentIndex > 0) {
                currentIndex = Math.max(0, currentIndex - 20);
                viewStoreReview();
            } else {
                System.out.println("이전 화면이 없습니다.");
                System.out.println("Enter를 누르면 이전 화면으로 돌아갑니다.");
        		scan.nextLine();
            }
		} else {
			System.out.println("잘못 입력하셨습니다.");
			System.out.println("Enter를 누르면 이전 화면으로 돌아갑니다.");
			scan.nextLine();
        }
    }
}


/*
package com.revolving.member;

import java.util.Scanner;

import com.revolving.Main;
import com.revolving.data.MemberData;
import com.revolving.data.RatingData;
import com.revolving.data.StoreData;
import com.revolving.data.object.Member;
import com.revolving.data.object.Rating;
import com.revolving.data.object.Store;

public class MenuRate {

	static Scanner scan = new Scanner(System.in);
	
	public static void viewStoreReview() {
		
		Main.printMenu("매장 평가 열람");

		for (Rating review : RatingData.list) {
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
			System.out.printf("점수: %s\n", review.getScore());
		}
		
		/*
		System.out.println("[회원명][식당명][주소][리뷰][점수]");
		for (Rating review : RatingData.list) {
			for (Member member : MemberData.list) {
				if (member.getNo().equals(review.getMemberNo())) {
					System.out.printf("%s ", member.getName());
					break;
				}
			}
			for (Store store : StoreData.list) {
				if (store.getNo().equals(review.getStoreNo())) {
					System.out.printf("%s %s %s ", store.getName(), store.getAddress(), store.getTel());
					break;
				}
			}
			System.out.printf("%s %s\r\n", review.getReview(), review.getScore());
		}
	}
}
*/