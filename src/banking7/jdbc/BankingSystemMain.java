package banking7.jdbc;

import java.util.Scanner;

public class BankingSystemMain {

	//키보드입력을 위한 Scanner인스턴스 생성
	public static Scanner sc = new Scanner(System.in);

	//계좌정보를 저장하기 위한 인스턴스배열생성
	static Account[] accountArr = new Account[50];
	
	static AccountDAO dao = new AccountDAO();

	//개설된 계좌정보를 카운트하기 위한 변수
	static int accCount = 0;

	//메뉴출력
	public static void showMenu() {
		System.out.println("-----Menu-----");
		System.out.println("1.계좌개설");
		System.out.println("2.입	금");
		System.out.println("3.출	금");
		System.out.println("4.계좌정보출력");
		System.out.println("5.프로그램종료");
		System.out.print("선택:");
	}

	// 계좌개설을 위한 함수
	public static void makeAccount() {
		System.out.print("계좌번호:");
		String accID = sc.nextLine();
		System.out.print("고객이름:");
		String cusName = sc.nextLine();
		System.out.print("잔고:");
		int accMoney = sc.nextInt();

		//신규정보를 통한 객체생성
		Account addAcc =
			new Account(accID, cusName, accMoney);
		//객체배열에 저장후 카운트변수 1 증가
		//accountArr[accCount++] = addAcc;
		dao.makeAccount(addAcc);		

		System.out.println("신규계좌개설이 완료되었습니다.");
		System.out.println();
	}

	// 입    금
	public static void depositMoney() {

		System.out.println("계좌번호와 입금할 금액을 입력하세요");

		System.out.print("계좌번호:");
		String accID = sc.nextLine();
		System.out.print("입금액:");
		int money = sc.nextInt();

		//해당계좌번호가 객체배열에 있는지 검색
//		for(int i=0 ; i<accCount ; i++ )
//		{
//			//해당 계좌번호가 있다면...
//			if(accID.compareTo(accountArr[i].getAccountID())==0)
//			{
//				//입금처리
//				accountArr[i].plusAccMoney(money);
//			}
//		}
		int isAffected = dao.depositMoney(accID, money);
		if(isAffected>0)
			System.out.println("입금이 완료되었습니다.");
	}
	// 출    금
	public static void withdrawMoney() {

		System.out.println("계좌번호와 출금할 금액을 입력하세요");

		System.out.print("계좌번호:");
		String accID = sc.nextLine();
		System.out.print("출금액:");
		int money = sc.nextInt();

		//해당계좌번호가 객체배열에 있는지 검색
//		for(int i=0 ; i<accCount ; i++ )
//		{
//			//해당 계좌번호가 있다면...
//			if(accID.compareTo(accountArr[i].getAccountID())==0)
//			{
//				//출금처리
//				accountArr[i].minusAccMoney(money);
//			}
//		}
		 
		int isAffected = dao.withdrawMoney(accID, money);
		if(isAffected>0)
			System.out.println("출금이 완료되었습니다.");
	}

	//전체계좌조회
	public static void showAccInfo() {
//		for(int i=0 ; i<accCount ; i++ )
//		{
//			accountArr[i].showAccountInfo();
//		}
		dao.showAccInfo();
		System.out.println("전체계좌정보가 출력되었습니다.");
		System.out.println("***************************");
	}

	public static void main(String[] args) {

		while(true) {
			//메뉴출력
			showMenu();

			//메뉴입력
			int keyInput = sc.nextInt();
			sc.nextLine();//버퍼날림

			if(keyInput==MenuChoice.MAKE) {
				System.out.println("***계좌개설***");
				makeAccount();
			}
			else if(keyInput==MenuChoice.DEPOSIT) {
				System.out.println("***입 금***");
				depositMoney();
			}
			else if(keyInput==MenuChoice.WITHDRAW) {
				System.out.println("***출 금***");
				withdrawMoney();
			}
			else if(keyInput==MenuChoice.INQUIRE) {
				System.out.println("***계좌정보출력***");
				showAccInfo();
			}
			else if(keyInput==MenuChoice.EXIT) {
				System.out.println("***프로그램종료***");
				dao.close();
				System.exit(0);
			}
		}
	}
}









