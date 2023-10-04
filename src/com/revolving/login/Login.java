package com.revolving.login;

import java.util.Scanner;

import com.revolving.Main;
import com.revolving.admin.AdminMenu;
import com.revolving.data.MemberData;
import com.revolving.data.object.Member;
import com.revolving.member.MemberMenu;

public class Login {

    static Scanner scan = new Scanner(System.in);
    public static Member user;
    
	public static void login() {
		Main.printMenu("로그인");
		System.out.print("아이디: ");
		String id = scan.nextLine().trim();

		System.out.print("비밀번호: ");
		String pwd = scan.nextLine().trim();
		
		if (id.equals("admin") && pwd.equals("admin")) { // 관리자 로그인
			System.out.println("관리자로 로그인 되었습니다.");
			System.out.println("Enter를 누르면 관리자 메뉴로 이동합니다.");
			scan.nextLine();

			AdminMenu.adminMenu(); // 관리자 기능 실행
		} else { // 회원 로그인
			user = getMember(id, pwd);
			
			if (user != null) {
				MemberMenu.menu(); // 회원 기능 실행
			} else {
				System.out.println("Enter를 누르면 이전 화면으로 돌아갑니다.");
	            scan.nextLine();
			}
		}
	}
	
	private static Member getMember(String id, String pwd) {
        for (Member member : MemberData.list) {
            if (member.getId().equals(id) && member.getPwd().equals(pwd)) {
            	System.out.println(member.getName() + " 회원님 환영합니다.");
                return member;
            }
        }
        return null;
    }
}
