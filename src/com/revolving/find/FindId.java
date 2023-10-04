package com.revolving.find;

import java.util.Scanner;

import com.revolving.Main;
import com.revolving.data.MemberData;
import com.revolving.data.object.Member;

public class FindId {

	static Scanner scan;
	
	static {
		scan = new Scanner(System.in);
	}
	
	static public void findId() {
		
		Main.printMenu("아이디 찾기");
		
		System.out.print("회원이름: ");
		String name = scan.nextLine().trim();
		System.out.println();
		System.out.print("전화번호(숫자만 입력): ");
		String tel = scan.nextLine().trim();
		System.out.println();
		System.out.print("생년월일(숫자만 입력): ");
		String birth = scan.nextLine().trim();
		Main.printLine();
		
		for (Member member : MemberData.list) {
			if (member.getName().equals(name) && member.getTel().equals(tel) && member.getBirth().equals(birth)) {
				System.out.printf("회원님의 아이디: %s\r\n", member.getId());
				System.out.println("엔터를 입력하면 초기 화면으로 돌아갑니다.");
				Main.printLine();
				scan.nextLine().trim();
				return;
			} 
		}
		
		System.out.println("일치하는 회원정보가 없습니다.");
		System.out.println("엔터를 입력하면 초기 화면으로 돌아갑니다.");
		Main.printLine();
		scan.nextLine().trim();
		
	}
	
}
