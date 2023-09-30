package com.revolving.join;

import java.util.Scanner;

import com.revolving.Main;
import com.revolving.data.MemberData;
import com.revolving.data.object.Member;

public class Join {

	static Scanner scan = new Scanner(System.in);
	
	public static void join() {
		Main.printMenu("회원 가입");
		System.out.println("회원 가입을 진행합니다.");
		System.out.println("ID, 비밀번호, 이름, 전화번호, 생년월일 정보를 입력합니다.");
		
		String id = inputId();
        String pw = inputPw();
        String name = inputName();
        String tel = inputTel();
        String birth = inputBirth();
		
        MemberData.addMember(name, id, pw, tel, birth);
        MemberData.save();
        System.out.println(name + " 회원님, 회원 가입을 축하드립니다!");
	}
	
	private static String inputId() {
		Main.printMenu("아이디 입력");
		System.out.println("4자 ~ 16자의 영문 대소문자, 숫자를 포함하여 입력하세요.");
		System.out.print("아이디 입력: ");
		String id = scan.nextLine().trim();

		if (!isValidId(id)) {
			System.out.println("형식에 맞지 않는 아이디입니다.");
			inputId();
		}

		for (Member member : MemberData.list) {
			if (member.getId().equals(id)) {
				System.out.println("이미 존재하는 아이디입니다.");
				inputId();
				break;
			}
		}
		
		return id;
	}

	private static String inputPw() {
		Main.printMenu("비밀번호 입력");
		System.out.println("9자 ~ 14자의 영문 대소문자, 숫자, 특수문자(!, #, ^, &, *)를 포함하여 입력하세요.");
		System.out.print("비밀번호 입력: ");
		String pw = scan.nextLine().trim();

		if (!isValidPw(pw)) {
			System.out.println("형식에 맞지 않는 비밀번호입니다.");
			inputPw();
		}

		System.out.println("비밀번호가 저장되었습니다.");
		
		return pw;
	}

	private static String inputName() {
		Main.printMenu("이름 입력");
		System.out.println("2자 ~ 4자의 한글 이름을 입력하세요.");
		System.out.print("이름 입력: ");
		String name = scan.nextLine().trim();

		if (!isValidName(name)) {
			System.out.println("형식에 맞지 않는 이름입니다.");
			inputName();
		}
		
		return name;
	}

    private static String inputTel() {
		Main.printMenu("전화번호 입력");
		System.out.println("11자의 숫자로 전화번호를 입력하세요.");
		System.out.print("전화번호 입력: ");
		String tel = scan.nextLine().trim();

		if (!isValidTel(tel)) {
			System.out.println("형식에 맞지 않는 전화번호입니다.");
			inputTel();
		}
		
		return tel;
	}
    
    private static String inputBirth() {
		Main.printMenu("생년월일 입력");
		System.out.println("6자의 숫자로 생년월일을 입력하세요.");
		System.out.print("생년월일 입력: ");
		String birth = scan.nextLine().trim();

		if (!isValidBirth(birth)) {
			System.out.println("형식에 맞지 않는 생년월일입니다.");
			inputBirth();
		}
		
		return birth;
	}
	
	public static boolean isValidId(String id) {
		return id.matches("^[a-zA-Z0-9]{4,16}$");
	}
	
	public static boolean isValidPw(String pw) {
		return pw.matches("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!#^&*])[a-zA-Z\\d!#^&*]{9,14}$");
	}
	
	public static boolean isValidName(String name) {
	    return name.matches("^[가-힣]{2,4}$");
	}
	
	public static boolean isValidTel(String tel) {
	    return tel.matches("^\\d{11}$");
	}
	
	public static boolean isValidBirth(String birth) {
	    return birth.matches("^\\d{6}$");
	}
}
