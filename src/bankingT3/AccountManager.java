package bankingT3;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
		
		System.out.println("6.자동저장 ");
		
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
	
	//컬렉션에 저장된정보를 Text파일로 저장하기 
	public void saveInfoTxt() {
		try {
			//입력스트림없이 출력스트림만 있는 클래스 
			PrintWriter out = new PrintWriter(
				new FileWriter("src/bankingT3/AutoSaveAccount.txt"));
			
			//앞에서 txt파일쪽으로 생성한 스트림을 통해 내용을 입력한다.
			for(Account ac : hashSet) {				
				out.println(ac);
			}
			
			System.out.println("자동 저장 되었습니다.");
			out.close();
		}
		catch (IOException e) {
			System.out.println("Text파일로 저장 중 예외발생");
			e.printStackTrace();
		}
	}
	
	public void dataSaveOption(AutoSaver as) {
		System.out.println("저장옵션선택");
		System.out.print("1.On, 2.Off");
		
		Scanner scanner = new Scanner(System.in);
		int menu = scanner.nextInt();
		if(menu==1) {				
			if(!as.isAlive()) {
				//데몬쓰레드도 만든 후 쓰레드를 시작한다. 
				as.setDaemon(true);
				as.start();
				System.out.println("자동저장을 시작합니다.");
			}
			else {
				System.out.println("자동 저장이 이미 실행중입니다.");
			}
		}
		else if(menu==2) {
			if(as.isAlive()) {
				as.interrupt();
				System.out.println("자동 저장을 종료합니다.");
			}
		}
		else {
			System.out.println("메뉴 입력을 잘못하셨습니다.");
		}
	}
}




