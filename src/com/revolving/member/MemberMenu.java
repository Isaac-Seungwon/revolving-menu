package com.revolving.member;

import java.util.Scanner;

import com.revolving.Main;

public class MemberMenu {

   static Scanner scan = new Scanner(System.in);

   public static void menu() {

      while (true) {
         Main.printMenu("회원 메뉴");
         Main.printOption("메뉴 추천", "메뉴 목록 정렬", "후기 작성", "사용자들의 평가 열람", "마이페이지");
         String input = scan.nextLine().trim();

         if (input.equals("1")) {
            
         } else if (input.equals("2")) {
            
         } else if (input.equals("3")) {
            
         } else if (input.equals("4")) {
        	//MenuRate.SelectViewReview();
         } else if (input.equals("5")) {
            
         } else {
            return; //Main.main()
         }
      }
   }
}