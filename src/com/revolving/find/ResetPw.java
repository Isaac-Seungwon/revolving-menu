package com.revolving.find;

import java.util.Scanner;

import com.revolving.Main;
import com.revolving.data.MemberData;
import com.revolving.data.object.Member;
import com.revolving.join.Join;

public class ResetPw {

	static Scanner scan;
	
	static {
		scan = new Scanner(System.in);
	}
	
	static public void resetPw() {
		
		Main.printMenu("비밀번호 재설정");
		
		System.out.print("회원 이름: ");
		String name = scan.nextLine().trim();
		System.out.println();
		System.out.print("회원 아이디: ");
		String id = scan.nextLine().trim();
		Main.printLine();
		
		for (Member member : MemberData.list) {
			if (member.getName().equals(name) && member.getId().equals(id)) {
				while (true) {
					System.out.print("신규 비밀번호 입력: ");
					String pw = scan.nextLine().trim();
					if (!Join.isValidPw(pw)) {
						Main.printLine();
						System.out.println("형식에 맞지 않는 비밀번호입니다.");
						System.out.println("다시 입력해주세요.");
						Main.printLine();
						continue;
					}
					
					System.out.println();
					System.out.print("신규 비밀번호 재입력: ");
					
					if (scan.nextLine().trim().equals(pw)) {
						Main.printLine();
						member.setPwd(pw);
						System.out.println("비밀번호 재설정이 완료되었습니다.");
						System.out.println("엔터키를 입력하면 초기 화면으로 돌아갑니다.");
						Main.printLine();
						scan.nextLine().trim();
						MemberData.save();
						return;
					}
					Main.printLine();
					System.out.println("입력 받은 두 값이 다릅니다.");
					System.out.println("다시 입력해주세요.");
					Main.printLine();
				}
			}
		}
		
		System.out.println("일치하는 회원정보가 없습니다.");
		System.out.println("엔터키를 입력하면 초기 화면으로 돌아갑니다.");
		Main.printLine();
		scan.nextLine().trim();
	}
	
}