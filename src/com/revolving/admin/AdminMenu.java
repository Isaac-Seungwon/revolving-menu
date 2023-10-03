package com.revolving.admin;

import java.util.Scanner;

import com.revolving.Main;

public class AdminMenu {
   
   static Scanner scan = new Scanner(System.in);
   
   public static void Menu() {
      
      while(true) {
         Main.printMenu("관리자 메뉴");
         Main.printOption("메뉴 관리", "매장 관리", "회원 정보 관리", "카테고리 관리", "사용자의 매장 및 음식 평가 관리");
         
         String input = scan.nextLine();
         
         if (input.equals("1")) {
            // 메뉴 관리
         } else if (input.equals("2")) {
            // 매장 관리
         } else if (input.equals("3")) {
            // 회원 정보 관리
         } else if (input.equals("4")) {
            CategoryManagement.checkCategory();
         } else if (input.equals("5")) {
            RateManagement.checkRate();
         } else {
            return;
         }
      }
   }

}