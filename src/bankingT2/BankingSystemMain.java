package bankingT2;

import java.util.Scanner;

public class BankingSystemMain {
	
	public static void main(String[] args) {		
		
		Scanner scanner = new Scanner(System.in);
		AccountManager am = new AccountManager();
		
		while(true) {
			am.showMenu();		
			int choice = scanner.nextInt();		
			switch(choice) {
			case ICustomDefine.MAKE:
				System.out.println("계좌계설 선택");
				am.makeAccount();
				break;
			case ICustomDefine.DEPOSIT:
				System.out.println("입금 선택");
				am.depositMoney();
				break;
			case ICustomDefine.WITHDRAW:
				System.out.println("출금 선택");
				am.withdrawMoney();
				break;
			case ICustomDefine.INQUIRE:
				System.out.println("전체계좌정보출력 선택");
				am.showAccInfo();
				break;
			case ICustomDefine.EXIT:
				System.out.println("종료 선택");
				return;
			}
		}
	}
}
