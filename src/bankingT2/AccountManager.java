package bankingT2;

import java.util.HashSet;
import java.util.Scanner;

public class AccountManager {

	//Account 인스턴스를 저장할 수 있는 배열 
//	public Account accArr[] = new Account[50];
	//인덱스로 사용할 변수 
//	public int accCnt = 0;
	
	//기존 인스턴스배열을 컬렉션 기반으로 변경 
	HashSet<Account> hashSet = new HashSet<Account>();
	
	public void showMenu() {
		System.out.println("######## 메뉴를 입력하세요 ########");
		System.out.println("1.계좌계설 ");
		System.out.println("2.입금");
		System.out.println("3.출금 ");
		System.out.println("4.전체계좌정보출력");
		System.out.println("5.프로그램종료 ");
		System.out.print("메뉴선택>>>");
	}	
	// 계좌개설을 위한 함수 
	public void makeAccount() {
		System.out.println("==makeAccount()호출됨==");
		Scanner scanner = new Scanner(System.in);
		//정보 입력 받기
		System.out.print("계좌번호:");
		String accNum = scanner.nextLine();
		System.out.print("고객이름:");
		String name = scanner.nextLine();
		System.out.print("잔고:");
		int bal = scanner.nextInt();
		scanner.nextLine();
		//입력받은 정보를 통해 인스턴스 생성
		Account account = new Account(accNum, name, bal);
		
		//인스턴스 배열에 추가 
//		accArr[accCnt++] = account;
		
		//컬렉션에 인스턴스 추가
		boolean yn = hashSet.add(account);
		if(yn==true)
			System.out.println("입력되었습니다.");
		else {
			System.out.println("중복된 인스턴스가 발견되었습니다.");
			System.out.println("덮어쓸까요?");
			
			String con = scanner.nextLine();
			if(con.equalsIgnoreCase("y")) {
				//덮어쓰기 진행
				/*
				새롭게 입력한 인스턴스로 기존에 저장된 인스턴스를 삭제한다.
				우리의 입장에서는 서로 다른 인스턴스 이지만 Set의 입장에서는
				동일한 인스턴스이므로 삭제가 가능하다.  
				 */
				hashSet.remove(account);
				//새롭게 입력한 인스턴스를 추가한다. 
				hashSet.add(account);
			}
			else {
				//무시
			}			
		}
	}    
	// 입    금
	public void depositMoney() {
		System.out.println("==depositMoney()호출됨==");	
		
		//accArr[0].balance += 1000;
		//accArr[0].deposit(1000);
	} 
	// 출    금
	public void withdrawMoney() {
		System.out.println("==withdrawMoney()호출됨==");
		//accArr[0].balance -= 1000;
	} 
	// 전체계좌정보출력
	public void showAccInfo() {
		System.out.println("==showAccInfo()호출됨==");
		for(Account ac : hashSet) {
			ac.showAccount();
		}
	}
}




