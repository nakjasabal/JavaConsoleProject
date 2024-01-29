package banking3;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BankingSystemMain {

	public static void main(String[] args) {

		//AccountManager 인스턴스 생성
		AccountManager ac = new AccountManager();

		while(true) {

			//메뉴출력
			ac.showMenu();

			try {

				//메뉴입력
				int keyInput = ac.sc.nextInt();
				ac.sc.nextLine();//버퍼날림

				if(keyInput==MenuChoice.MAKE) {
					System.out.println("***계좌개설***");
					ac.makeAccount();
				}
				else if(keyInput==MenuChoice.DEPOSIT) {
					System.out.println("***입 금***");
					ac.depositMoney();
				}
				else if(keyInput==MenuChoice.WITHDRAW) {
					System.out.println("***출 금***");
					ac.withdrawMoney();
				}
				else if(keyInput==MenuChoice.INQUIRE) {
					System.out.println("***계좌정보출력***");
					ac.showAccInfo();
				}
				else if(keyInput==MenuChoice.EXIT) {
					System.out.println("***프로그램종료***");
					System.exit(0);
				}
				else {
					//메뉴이외의 다른 숫자를 입력했을때
					//사용자정의 예외를 발생시킨다.
					MenuSelectException ex =
						new MenuSelectException();
					throw ex;
				}
			}
			catch(MenuSelectException e) {
				System.out.println(e.getMessage());
				System.out.println("메뉴는 1~5사이의 "
						+ "정수를 입력하세요");
			}
			catch(InputMismatchException e) {
				System.out.println("입력은 숫자로만 하세요.");
				ac.sc.nextLine();//버퍼날림
			}
		}
	}
}









