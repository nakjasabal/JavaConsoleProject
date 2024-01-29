package banking2;

import java.util.Scanner;

public class BankingSystemMain {

	public static void main(String[] args) {

		//AccountManager 인스턴스 생성
		AccountManager ac = new AccountManager();

		while(true) {
			//메뉴출력
			ac.showMenu();

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
		}
	}
}









