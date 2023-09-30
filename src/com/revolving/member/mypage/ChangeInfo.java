package com.revolving.member.mypage;

import java.util.Scanner;

import com.revolving.Main;
import com.revolving.data.MemberData;
import com.revolving.data.object.Member;
import com.revolving.join.Join;
import com.revolving.login.Login;

/**
 * 회원 정보 수정 클래스
 * 목적: 사용자의 회원 정보(ID, 비밀번호, 이름, 전화번호, 생년월일)를 수정한다.
 * 
 * @author 이승원
 */
public class ChangeInfo {

	static Scanner scan = new Scanner(System.in);
	
	/**
	 * 회원 정보를 수정하는 메서드
	 * 아이디, 비밀번호, 이름, 전화번호, 생년월일 중 하나를 선택하여 수정할 수 있다.
	 */
	public static void changeUserInfo() {

		while (true) {
			Main.printMenu("회원 정보 수정");
			Main.printOption("아이디 수정", "비밀번호 수정", "이름 수정", "전화번호 수정", "생년월일 수정");
			String input = scan.nextLine().trim();

			if (input.equals("1")) {
				changeId();
			} else if (input.equals("2")) {
				changePw();
			} else if (input.equals("3")) {
				changeName();
			} else if (input.equals("4")) {
				changeTel();
			} else if (input.equals("5")) {
				changeBirthdate();
			} else {
				return;
			}
			scan.nextLine();
		}
	}

	/**
	 * 아이디를 수정하는 메서드
	 * 새로운 아이디를 입력받고 유효성을 검사하여 아이디를 수정한다.
	 */
	private static void changeId() {
		Main.printMenu("아이디 수정");
		System.out.println("4자 ~ 16자의 영문 대소문자, 숫자를 포함하여 입력하세요.");
		System.out.print("새로운 아이디 입력: ");
		String newId = scan.nextLine().trim();

		if (!Join.isValidId(newId)) {
			System.out.println("형식에 맞지 않는 아이디입니다.");
			return;
		}

		for (Member member : MemberData.list) {
			if (member.getId().equals(newId)) {
				System.out.println("이미 존재하는 아이디입니다.");
				return;
			}
		}

		Login.user.setId(newId);
        MemberData.save();
		System.out.println("아이디를 수정했습니다.");
	}

	/**
	 * 비밀번호를 수정하는 메서드
	 * 새로운 비밀번호를 입력받고 유효성을 검사하여 비밀번호를 수정한다.
	 */
	private static void changePw() {
		Main.printMenu("비밀번호 수정");
		System.out.println("9자 ~ 14자의 영문 대소문자, 숫자, 특수문자(!, #, ^, &, *)를 포함하여 입력하세요.");
		System.out.print("새로운 비밀번호 입력: ");
		String newPw = scan.nextLine().trim();

		if (!Join.isValidPw(newPw)) {
			System.out.println("형식에 맞지 않는 비밀번호입니다.");
			return;
		}

		Login.user.setPwd(newPw);
        MemberData.save();
		System.out.println("비밀번호를 수정했습니다.");
	}

	/**
	 * 이름을 수정하는 메서드
	 * 새로운 이름을 입력받고 유효성을 검사하여 이름을 수정한다.
	 */
	private static void changeName() {
	    Main.printMenu("이름 수정");
	    System.out.println("2자 ~ 4자의 한글 이름을 입력하세요.");
	    System.out.print("새로운 이름 입력: ");
	    String newName = scan.nextLine().trim();

	    if (!Join.isValidName(newName)) {
	        System.out.println("형식에 맞지 않는 이름입니다.");
	        return;
	    }

	    Login.user.setName(newName);
        MemberData.save();
	    System.out.println("이름을 수정했습니다.");
	}

	/**
	 * 전화번호를 수정하는 메서드
	 * 새로운 전화번호를 입력받고 유효성을 검사하여 전화번호를 수정한다.
	 */
	private static void changeTel() {
	    Main.printMenu("전화번호 수정");
	    System.out.println("11자의 숫자로 전화번호를 입력하세요.");
	    System.out.print("새로운 전화번호 입력: ");
	    String newTel = scan.nextLine().trim();

	    if (!Join.isValidTel(newTel)) {
	        System.out.println("형식에 맞지 않는 전화번호입니다.");
	        return;
	    }

	    Login.user.setTel(newTel);
        MemberData.save();
	    System.out.println("전화번호를 수정했습니다.");
	}

	/**
	 * 생년월일을 수정하는 메서드
	 * 새로운 생년월일을 입력받고 유효성을 검사하여 생년월일을 수정한다.
	 */
	private static void changeBirthdate() {
	    Main.printMenu("생년월일 수정");
	    System.out.println("6자의 숫자로 생년월일을 입력하세요.");
	    System.out.print("새로운 생년월일 입력: ");
	    String newBirthdate = scan.nextLine().trim();

	    if (!Join.isValidBirth(newBirthdate)) {
	        System.out.println("형식에 맞지 않는 생년월일입니다.");
	        return;
	    }

	    Login.user.setBirth(newBirthdate);
        MemberData.save();
	    System.out.println("생년월일을 수정했습니다.");
	}
}
