package bankingT3;

import java.util.Scanner;

public class BankingSystemMain {
	
	public static void main(String[] args) {		
		
		Scanner scanner = new Scanner(System.in);
		AccountManager am = new AccountManager();
		
		AutoSaver as = null;
		
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
			case ICustomDefine.AUTO_SAVE:
				System.out.println("자동 저장 선택");
				//파일저장 확인하기 
				//am.saveInfoTxt();
				
				try {
					//인스턴스가 생성되지 않은 상태에서 isAlive()를 호출하면 
					//예외가 발생하므로 catch절에서 인스턴스를 생성한다. 
					if(!as.isAlive()) {
						as = new AutoSaver(am);
					}
				}
				catch (Exception e) {
					System.out.println("AutoSaver 인스턴스 생성되지 않음");
					as = new AutoSaver(am);
				}
				System.out.println("쓰레드="+ as);
				//메니져 클래스의 메서드를 호출하면서 쓰레드 인스턴스를 전달 
				am.dataSaveOption(as);
			}
		}
	}
}
