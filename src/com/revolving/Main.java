package com.revolving;

import java.util.Scanner;

import com.revolving.data.Data;
import com.revolving.find.FindId;
import com.revolving.find.ResetPw;
import com.revolving.join.Join;
import com.revolving.login.Login;

public class Main {
    static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {

		Data.allLoad(); // 데이터 불러오기
		
		while(true) {
			System.out.println();
			System.out.println("                  _ .-') _                                           _   .-')      _ (`-.    ('-.         .-') _ ");
			System.out.println("		 ( (  OO) )                                         ( '.( OO )_   ( (OO  )  ( OO ).-.    ( OO ) )" );
			System.out.println("		  \\     .'_  .-'),-----.  ,--.      ,--.      ,-.-') ,--.   ,--.)_.`     \\ / . --. /,--./ ,--,'");
			System.out.println("		  ,`'--..._)( OO'  .-.  ' |  |.-')  |  |.-')  |  |OO)|   `.'   |(__...--''  | \\-.  \\ |   \\ |  |\\ ");
			System.out.println("		  |  |  \\  '/   |  | |  | |  | OO ) |  | OO ) |  |  \\|         | |  /  | |.-'-'  |  ||    \\|  | )");
			System.out.println("		  |  |   ' |\\_) |  |\\|  | |  |`-' | |  |`-' | |  |(_/|  |'.'|  | |  |_.' | \\| |_.'  ||  .     |/");
			System.out.println("		  |  |   / :  \\ |  | |  |(|  '---.'(|  '---.',|  |_.'|  |   |  | |  .___.'  |  .-.  ||  |\\    |");
			System.out.println("		  |  '--'  /   `'  '-'  ' |      |  |      |(_|  |   |  |   |  | |  |       |  | |  ||  | \\   | ");
			System.out.println("		  `-------'      `-----'  `------'  `------'  `--'   `--'   `--' `--'        `--' `--'`--'  `--'" );
			
			Main.printLine();
			Main.printOption("로그인", "회원가입", "아이디 찾기", "비밀번호 재설정");
			String input = scan.nextLine().trim();
	
			if (input.equals("1")) {
				Login.login();
			} else if (input.equals("2")) {
				Join.join();
			} else if (input.equals("3")) {
				FindId.findId();
			} else if (input.equals("4")) {
				ResetPw.resetPw();
			} else {
				System.out.println();
				System.out.println("  ;)( ;              ");
				System.out.println("  :----:     o8Oo.   ");
				System.out.println(" C|====| ._o8o8o8Oo_.");
				System.out.println("  |    |  \\========/");
				System.out.println("  `----'   `------'  ");
				System.out.println("다음에 다시 만나요~ :)");
				return;
			}
		}
	}
	
	/**
	 * 메뉴의 주어진 옵션과 '돌아가기' 옵션을 출력하는 메소드
	 * @param options 출력할 옵션
	 */
	public static void printOption(String... options) {
		for (int i = 0; i < options.length; i++) {
			System.out.println((i + 1) + ". " + options[i]);
		}
		System.out.println("0. 돌아가기");
		Main.printLine();
		System.out.print("번호 입력: ");
	}
	
	/**
	 * 주어진 문자열을 가운데 정렬하여 출력하는 메소드
	 * @param text 출력할 문자열
	 */
	public static void printMenu(String text) {
		String line = "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━";
		int totalWidth = line.length() / 2; // 총 출력 폭 설정(75)

		String formattedText = centerText(text, totalWidth);

		Main.printLine();
		System.out.println(formattedText);
		Main.printLine();
	}

	/**
	 * 주어진 문자열을 주어진 폭에 맞게 가운데 정렬하여 반환하는 메소드
	 * @param text  가운데 정렬할 문자열
	 * @param width 문자열을 정렬할 폭
	 * @return 가운데 정렬된 문자열
	 */
	public static String centerText(String text, int width) {
		return String.format("%" + width + "s", text);
	}
	
	/**
	 * 가운데 정렬된 선을 출력하는 메소드
	 */
	public static void printLine() {
	    String line = "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━";
	    System.out.println(line);
	}
}