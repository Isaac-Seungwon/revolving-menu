package com.revolving.member;

import java.util.Scanner;

import com.revolving.Main;

public class MenuSort {
	
	static Scanner scan = new Scanner(System.in);

	public static void mesuSort() {

		while (true) {
			Main.printMenu("정렬 기준 선택");
			Main.printOption("카테고리별 정렬", "가격대별 정렬", "매장 평가 점수별 정렬", "음식 평가 점수별 정렬", "계절별 정렬(제철음식 보기)");
			String input = scan.nextLine();
	
			if (input.equals("1")) {
				sortByCategory();
			} else if (input.equals("2")) {
				sortByPriceRange();
			} else if (input.equals("3")) {
				sortByStoreEvaluationScore();
			} else if (input.equals("4")) {
				sortByFoodEvaluationScore();
			} else if (input.equals("5")) {
				sortBySeason();
			} else {
				return;
			}

			System.out.println("Enter를 누르면 이전 화면으로 돌아갑니다.");
			scan.nextLine();
		}
	}
	
	private static void sortByCategory() {
		// TODO Auto-generated method stub
		
	}
	
	private static void sortByPriceRange() {
		// TODO Auto-generated method stub
		
	}

	private static void sortByStoreEvaluationScore() {
		// TODO Auto-generated method stub
		
	}

	private static void sortByFoodEvaluationScore() {
		// TODO Auto-generated method stub
		
	}

	private static void sortBySeason() {
		// TODO Auto-generated method stub
		
	}
}