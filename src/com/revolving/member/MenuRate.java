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
            
            if (i != endIndex - 1) {
                System.out.println();
            }
        }

        
        extracted(endIndex);
    }

	private static void extracted(int endIndex) {
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
	                viewStoreReview();
	            } else {
	                System.out.println("다음 후기가 없습니다.");
	                scan.nextLine();
	            }
	        } else if (input.equals("2")) {
	            if (currentIndex > 0) {
	                currentIndex = Math.max(0, currentIndex - 20);
	                viewStoreReview();
	            } else {
	                System.out.println("이전 후기가 없습니다.");
	                scan.nextLine();
	            }
	        } else {
	            System.out.println("Enter를 누르면 이전 화면으로 돌아갑니다.");
	            scan.nextLine();
	            break;
	        }
        }
	}
}