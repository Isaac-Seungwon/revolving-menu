package com.revolving.member;

import java.util.Scanner;

import com.revolving.Main;
import com.revolving.data.RatingData;
import com.revolving.data.object.Rating;

public class MenuRate {

	static Scanner scan = new Scanner(System.in);
	
	public static void viewStoreReview() {
		
		Main.printMenu("매장 평가 열람");
		
		for (Rating review : RatingData.list) {
			System.out.printf("%s %s %s %s\r\n", review.getMemberNo(), review.getStoreNo(), review.getReview(), review.getScore());
		}
	}
}
