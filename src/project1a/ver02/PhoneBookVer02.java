package project1a.ver02;

import java.util.Scanner;

public class PhoneBookVer02 {

	//키보드를 통한 입력을 위한 객체생성
	/*
	static형태로 선언하였으므로 별도의 객체생성없이 사용이
	가능하며, 클래스 전체 영역에서 접근이 가능하다.
	즉, 각 지역마다 객체를 생성할 필요가 없다.
	 */
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {


		while(true) {

			System.out.println("메뉴를 선택하세요");
			System.out.println("1.주소록입력");
			System.out.println("2.프로그램종료");
			System.out.print("메뉴선택:");

			int iMenu = sc.nextInt();
			sc.nextLine();//버퍼날림

			if(iMenu==1) {
				//주소록을 입력함
				dataInput();
			}
			else if(iMenu==2) {
				//프로그램을 종료함
				System.out.println("프로그램을 종료합니다.");
				//System.exit(0);
				break;
			}
			else {
				System.out.println("==선택오류==");
				System.out.println("1,2중 하나를 선택하삼");
			}
		}

		/*PhoneInfo p1 =
			new PhoneInfo("토니 스타크", "010-1111-2222",
					"70-01-01");
		PhoneInfo p2 =
			new PhoneInfo("브루스 베너", "010-2222-3333");

		p1.showPhoneInfo();
		p2.showPhoneInfo();*/

	}//end of main

	public static void dataInput() {
		System.out.print("이름:");
		String name = sc.nextLine();
		System.out.print("전화번호:");
		String tel = sc.nextLine();
		System.out.print("생년월일:");
		String birth = sc.nextLine();

		PhoneInfo phoneInfo =
				new PhoneInfo(name, tel, birth);
		System.out.println("입력하신 정보는...");
		phoneInfo.showPhoneInfo();
	}//end of dataInput

}//end of class








