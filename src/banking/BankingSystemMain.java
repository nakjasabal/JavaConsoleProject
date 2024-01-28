package banking;

import java.util.InputMismatchException;

public class BankingSystemMain {

	public static void main(String[] args) {

		System.out.println("1차프로젝트(학원)");
		System.out.println("클론 후 작업01");
		System.out.println("pull없이 작업진행함");
		
		//AccountManager 인스턴스 생성
		AccountManager ac = new AccountManager();
		AutoSaver as = null;
		/*
		프로그램이 실행된후 PhoneBookManager의 객체가
		생성될때 readFile()메소드를 호출하여
		PhoneBook.obj파일을 읽어 객체화 한다.
		 */
		ac.readFile();

		while(true) {

			//메뉴출력
			ac.showMenu();

			try {
				//메뉴입력
				int keyInput = ac.sc.nextInt();
				ac.sc.nextLine();//버퍼날림

				if(keyInput==ICustomDefine.MAKE) {
					System.out.println("***계좌개설***");
					ac.makeAccount();
				}
				else if(keyInput==ICustomDefine.DEPOSIT) {
					System.out.println("***입 금***");
					ac.depositMoney();
				}
				else if(keyInput==ICustomDefine.WITHDRAW) {
					System.out.println("***출 금***");
					ac.withdrawMoney();
				}
				else if(keyInput==ICustomDefine.INQUIRE) {
					System.out.println("***계좌정보출력***");
					ac.showAccInfo();
				}
				else if(keyInput==ICustomDefine.THREE_BY_3) {//3by3게임
					System.out.println("***3by3게임을 시작합니다***");
					ac.gameStart();					
				}
				else if(keyInput==ICustomDefine.AUTO_SAVE) {//자동저장
					System.out.println("***자동저장을 시작합니다***");
					try {
						if(!as.isAlive()) {
							as = new AutoSaver(ac);
						}
					}
					catch (Exception e) {
						System.out.println("AutoSaver예외발생");
						as = new AutoSaver(ac);
					}
					System.out.println("쓰레드="+ as);
					ac.dataSaveOption(as);			
				}
				else if(keyInput==ICustomDefine.EXIT) {
					System.out.println("***프로그램종료***");
					ac.saveFile();
					System.exit(0);
				}
				else {
					//메뉴이외의 다른 숫자를 입력했을때
					//사용자정의 예외를 발생시킨다.
					MenuSelectException ex = new MenuSelectException();
					throw ex;
				}
			}
			catch(MenuSelectException e) {
				System.out.println(e.getMessage());
				System.out.println("메뉴는 1~5사이의 정수를 입력하세요");
			}
			catch(InputMismatchException e) {
				System.out.println("입력은 숫자로만 하세요.");
				e.printStackTrace();
				ac.sc.nextLine();//버퍼날림
			}
		}
	}

}
