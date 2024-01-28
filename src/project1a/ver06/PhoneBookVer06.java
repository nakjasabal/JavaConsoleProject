package project1a.ver06;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PhoneBookVer06 {

	public static void main(String[] args) {

		//핸들러 클래스 객체생성
		PhoneBookManager pm = new PhoneBookManager();

		while(true) {

			//선택할 메뉴를 출력한다.
			pm.printMenu();

			try{
				int iMenu = PhoneBookManager.sc.nextInt();
				PhoneBookManager.sc.nextLine();//버퍼날림
				
				if(iMenu>MenuItem.EXIT) {
					System.out.println("==선택오류==");
					System.out.println("1~5 중 하나를 선택하삼");
					MenuSelectException ex = new MenuSelectException();
					throw ex;
				}

				if(iMenu==MenuItem.INPUT) {
					System.out.println("==주소록을 입력함==");
					pm.dataInput();
				}
				else if(iMenu==MenuItem.SEARCH) {
					System.out.println("==주소록을 검색함==");
					pm.dataSearch();
				}
				else if(iMenu==MenuItem.DELETE) {
					System.out.println("==주소록을 삭제함==");
					pm.dataDelete();
				}
				else if(iMenu==MenuItem.PRINT) {
					System.out.println("==주소록을 출력함==");
					pm.dataAllShow();
				}
				else if(iMenu==MenuItem.EXIT) {
					//프로그램을 종료함
					System.out.println("프로그램을 종료합니다.");
					//System.exit(0);
					break;
				}				
			}
			catch(MenuSelectException e){
				System.out.println(e.getMessage());
				System.out.println("1~5사이의정수를입력하세요.");
			}
			catch(InputMismatchException e){
				System.out.println("입력은 숫자로하세요");
				System.out.println("문자는 허용되지 않습니다.");
				System.out.println("JVM메세지:"+e.getMessage());
				PhoneBookManager.sc.nextLine();//엔터키버퍼 날림
			}
		}

	}//end of main

}//end of class








