package banking3;

import java.util.Scanner;

public class AccountManager {

	//키보드입력을 위한 Scanner인스턴스 생성
	public static Scanner sc = new Scanner(System.in);

	//계좌정보를 저장하기 위한 인스턴스배열생성
	Account[] accountArr = new Account[50];

	//개설된 계좌정보를 카운트하기 위한 변수
	int accCount = 0;

	//메뉴출력
	public void showMenu() {
		System.out.println("==================Menu==================");
		System.out.print("1.계좌개설");
		System.out.print(", 2.입금");
		System.out.print(", 3.출금");
		System.out.print(", 4.계좌정보출력");
		System.out.println(", 5.종료");
		System.out.println("=========================================");
		System.out.print("선택:");
	}

	//2번째메뉴
	public void showMenu2nd() {
		System.out.println("-----계좌선택-----");
		System.out.println("1.보통계좌");
		System.out.println("2.신용신뢰계좌");
		System.out.print("선택:");
	}

	// 계좌개설을 위한 함수
	public void makeAccount() {

		//계좌개설의 두번째 메뉴 선택
		showMenu2nd();
		int keyInput = sc.nextInt();
		sc.nextLine();//버퍼날림

		System.out.print("계좌번호:");
		String accID = sc.nextLine();
		System.out.print("고객이름:");
		String cusName = sc.nextLine();
		System.out.print("잔고:");
		int accMoney = sc.nextInt();

		//부모클래스로 하위클래스의 인스턴스를 저장하기
		//위해 객체생성(이질화)
		Account addAcc = null;

		if(keyInput==1) {
			//보통계좌개설 선택
			System.out.print("기본이자%(정수형태):");
			int iRates = sc.nextInt();
			sc.nextLine();

			addAcc = new NormalAccount(accID, cusName,
					accMoney, iRates);
		}
		else if(keyInput==2) {
			//신용신뢰계좌 개설 선택
			System.out.print("기본이자%(정수형태):");
			int iRates = sc.nextInt();
			sc.nextLine();//버퍼날림
			System.out.print("신용등급(A,B,C):");
			String cGrade = sc.nextLine();

			addAcc = new HighCreditAccount(accID, cusName,
					accMoney, iRates, cGrade);
		}

		//객체배열에 저장후 카운트변수 1 증가
		accountArr[accCount++] = addAcc;

		System.out.println("신규계좌개설이 완료되었습니다.");
		System.out.println();
	}

	// 입    금
	public void depositMoney() {

		System.out.println("계좌번호와 입금할 금액을 입력하세요");

		System.out.print("계좌번호:");
		String accID = sc.nextLine();
		System.out.print("입금액:");
		int money = sc.nextInt();


		//입금액에 대한 부분 체크후 입금처리
		//1.음수는 입금불가
		if(money<=0) {
			System.out.println("음수는 입금불가");
			return;
		}
		//2.500단위만 입금가능
		if(money%500 != 0) {
			System.out.println("500원 단위로 입금가능함");
			return;
		}


		//해당계좌번호가 객체배열에 있는지 검색
		for(int i=0 ; i<accCount ; i++ )
		{
			//해당 계좌번호가 있다면...
			if(accID.compareTo(accountArr[i].getAccountID())==0)
			{
				//입금처리
				accountArr[i].plusAccMoney(money);
			}
		}

		System.out.println("입금이 완료되었습니다.");
	}
	// 출    금
	public void withdrawMoney() {

		System.out.println("계좌번호와 출금할 금액을 입력하세요");

		System.out.print("계좌번호:");
		String accID = sc.nextLine();
		System.out.print("출금액:");
		int money = sc.nextInt();
		sc.nextLine();//버퍼날림


		//출금액에 대한 부분 검증
		//1.음수는 출금불가
		if(money<=0) {
			System.out.println("음수는 출금불가");
			return;
		}
		if(money%1000 != 0) {
			System.out.println("1000원 단위만 출금가능");
			return;
		}


		//해당계좌번호가 객체배열에 있는지 검색
		for(int i=0 ; i<accCount ; i++ )
		{
			//해당 계좌번호가 있다면...
			if(accID.compareTo(accountArr[i].getAccountID())==0)
			{
				//출금처리
				accountArr[i].minusAccMoney(money);
			}
		}

		System.out.println("출금이 완료되었습니다.");
	}

	//전체계좌조회
	public void showAccInfo() {
		for(int i=0 ; i<accCount ; i++ )
		{
			accountArr[i].showAccountInfo();
		}
		System.out.println("전체계좌정보가 출력되었습니다.");
		System.out.println("***************************");
	}
}
