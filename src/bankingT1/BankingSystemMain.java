package bankingT1;

import java.util.Scanner;

public class BankingSystemMain {
	
	//Account 인스턴스를 저장할 수 있는 배열 
	public static Account accArr[] = new Account[50];
	//인덱스로 사용할 변수 
	public static int accCnt = 0;
	
	public static void showMenu() {
		System.out.println("######## 메뉴를 입력하세요 ########");
		System.out.println("1.계좌계설 ");
		System.out.println("2.입금");
		System.out.println("3.출금 ");
		System.out.println("4.전체계좌정보출력");
		System.out.println("5.프로그램종료 ");
		System.out.print("메뉴선택>>>");
	}	
	// 계좌개설을 위한 함수 
	public static void makeAccount() {
		System.out.println("==makeAccount()호출됨==");
		Scanner scanner = new Scanner(System.in);
		//정보 입력 받기
		System.out.print("계좌번호:");
		String accNum = scanner.nextLine();
		System.out.print("고객이름:");
		String name = scanner.nextLine();
		System.out.print("잔고:");
		int bal = scanner.nextInt();
		//입력받은 정보를 통해 인스턴스 생성
		Account account = new Account(accNum, name, bal);
		//인스턴스 배열에 추가 
		accArr[accCnt++] = account;
	}    
	// 입    금
	public static void depositMoney() {
		System.out.println("==depositMoney()호출됨==");	
		
		//accArr[0].balance += 1000;
		accArr[0].deposit(1000);
	} 
	// 출    금
	public static void withdrawMoney() {
		System.out.println("==withdrawMoney()호출됨==");
		
		accArr[0].balance -= 1000;
	} 
	// 전체계좌정보출력
	public static void showAccInfo() {
		System.out.println("==showAccInfo()호출됨==");
		for(int i=0 ; i<accCnt ; i++) {
			accArr[i].showAccount();
		}
	}
	public static void main(String[] args) {		
		Scanner scanner = new Scanner(System.in);		
		while(true) {
			showMenu();		
			int choice = scanner.nextInt();		
			switch(choice) {
			case ICustomDefine.MAKE:
				System.out.println("계좌계설 선택");
				makeAccount();
				break;
			case ICustomDefine.DEPOSIT:
				System.out.println("입금 선택");
				depositMoney();
				break;
			case ICustomDefine.WITHDRAW:
				System.out.println("출금 선택");
				withdrawMoney();
				break;
			case ICustomDefine.INQUIRE:
				System.out.println("전체계좌정보출력 선택");
				showAccInfo();
				break;
			case ICustomDefine.EXIT:
				System.out.println("종료 선택");
				return;
			}
		}
	}
}
