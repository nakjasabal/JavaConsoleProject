package banking6;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
 
public class AccountManager {

	//키보드입력을 위한 Scanner인스턴스 생성
	public static Scanner sc = new Scanner(System.in);

	/* 컬렉션을 사용하면 필요없는 코드

	//계좌정보를 저장하기 위한 인스턴스배열생성
	Account[] accountArr = new Account[50];
	//개설된 계좌정보를 카운트하기 위한 변수
	int accCount = 0;
	*/

	//컬렉션 사용하여 객체저장
	HashSet<Account> accountArr = new HashSet<Account>();
	
	//5단계 : I/O스트림을 통한 직렬화
	private final File dataFile = new File("src/banking6/AccountInfo.obj");


	//메뉴출력
	public void showMenu() {
		System.out.println("==================Menu==================");
		System.out.print("1.계좌개설");
		System.out.print(", 2.입금");
		System.out.print(", 3.출금");
		System.out.println(", 4.계좌정보출력");
		System.out.print("5.계좌정보삭제");
		System.out.print(", 6.저장옵션");
		System.out.print(", 7.3by3게임");
		System.out.println(", 8.종료");
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
		
		if(!(keyInput==1 || keyInput==2)) {
			System.out.println("메뉴 입력을 잘못하셨습니다. 다시 입력해주세요.");
			return;
		}

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

			addAcc = new NormalAccount(accID, cusName, accMoney, iRates);
		}
		else if(keyInput==2) {
			//신용신뢰계좌 개설 선택
			System.out.print("기본이자%(정수형태):");
			int iRates = sc.nextInt();
			sc.nextLine();//버퍼날림
			System.out.print("신용등급(A,B,C):");
			String cGrade = sc.nextLine();

			addAcc = new HighCreditAccount(accID, cusName, accMoney, iRates, cGrade);
		}

		//객체배열에 저장후 카운트변수 1 증가
		//accountArr[accCount++] = addAcc;

		//켈렉션을 통한 처리
		boolean isAdded = accountArr.add(addAcc);
		if(isAdded==true) {
			//중복된 계좌가 없을때는 정상입력됨
			System.out.println("계좌개설이 완료되었습니다.");
		}
		else {
			//중복된 계좌가 있을때 처리...
			System.out.println("중복계좌발견됨.");
			System.out.println("덮어쓸까요?(y or n)");
			String qu = sc.nextLine();
			if(qu.equalsIgnoreCase("y")) {
				//기존 객체를 제거함
				accountArr.remove(addAcc);
				//새로운 객체를 입력함
				accountArr.add(addAcc);
				System.out.println("새로운 정보로 갱신되었습니다.");
			}
			else {
				//기존정보를 유지한다.
				System.out.println("입력된 정보는 무시됩니다.");
			}
		}

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
		/*for(int i=0 ; i<accCount ; i++ )
		{
			//해당 계좌번호가 있다면...
			if(accID.compareTo(accountArr[i].getAccountID())==0)
			{
				//입금처리
				accountArr[i].plusAccMoney(money);
			}
		}	*/


		//반복자를 통한 입금처리

		//컬렉션의 내용을 iterator()를 통해 전달함
		Iterator<Account> itr = accountArr.iterator();

		//입금여부확인하기
		boolean isDeposit = false;

		//다음 객체가 있는지 검사
		while(itr.hasNext()) {
			//다음 객체를 꺼내서 참조변수에 저장
			Account curInfo = itr.next();
			//입력한 계좌번호와 객체의 계좌번호를 비교
			if(accID.compareTo(curInfo.getAccountID())==0) {
				//입력한 계좌가 있다면 입금처리
				if(curInfo.plusAccMoney(money)==true) {
					isDeposit = true;
				}
			}
		}

		if(isDeposit == true) {
			System.out.println("입금이 완료되었습니다.");
		}
		else {
			System.out.println("***입금이 취소됨***");
		}
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


		/*//해당계좌번호가 객체배열에 있는지 검색
		for(int i=0 ; i<accCount ; i++ )
		{
			//해당 계좌번호가 있다면...
			if(accID.compareTo(accountArr[i].getAccountID())==0)
			{
				//출금처리
				accountArr[i].minusAccMoney(money);
			}
		}	*/


		//컬렉션의 내용을 iterator()를 통해 전달함
		Iterator<Account> itr = accountArr.iterator();

		boolean isWithdraw = false;

		//다음 객체가 있는지 검사
		while(itr.hasNext()) {
			//다음 객체를 꺼내서 참조변수에 저장
			Account curInfo = itr.next();
			//입력한 계좌번호와 객체의 계좌번호를 비교
			if(accID.compareTo(curInfo.getAccountID())==0) {
				//입력한 계좌가 있다면 출금처리
				if(curInfo.minusAccMoney(money)==true) {
					isWithdraw = true;
				}
			}
		}

		if(isWithdraw==true)
			System.out.println("출금이 완료되었습니다.");
		else
			System.out.println("***출금이 취소됨***");
	}

	//전체계좌조회
	public void showAccInfo() {
		/*for(int i=0 ; i<accCount ; i++ )
		{
			accountArr[i].showAccountInfo();
		}*/


		//컬렉션의 내용을 iterator()를 통해 전달함
		Iterator<Account> itr = accountArr.iterator();
		//다음 객체가 있는지 검사
		while(itr.hasNext()) {
			//다음 객체를 꺼내서 참조변수에 저장
			Account curInfo = itr.next();
			curInfo.showAccountInfo();
		}


		System.out.println("전체계좌정보가 출력되었습니다.");
		System.out.println("***************************");
	}
	

	//계좌삭제
	public void deleteAccount() {
		boolean isDelete = false;
		System.out.println("삭제할 계좌번호를 입력하세요");
		System.out.print("계좌번호:");
		String accID = sc.nextLine();
		
		try {
			for(Account account : accountArr) {
				if(accID.equals(account.getAccountID())) {
					accountArr.remove(account);
					isDelete = true;
				}
			}
		}
		catch (Exception e) {
			System.out.println("ConcurrentModificationException 예외 발생됨");
		}
		
		if(isDelete==false) 
			System.out.println("일치하는 계좌가 없습니다.");
		else
			System.out.println("계좌를 삭제하였습니다.");
		System.out.println("***************************");
	}
	
	//폰북이 저장된 파일을 읽는다.
	public void readFile() {
		//만약 해당경로에 파일이 없다면 실행중지
		if(dataFile.exists()==false) {
			System.out.println("AccountInfo.obj 파일없음");
			return;
		}

		//파일이 존재한다면 읽어서 컬렉션에 저장한다.
		try {
			FileInputStream file = new FileInputStream(dataFile);
			ObjectInputStream in = new ObjectInputStream(file);
			while(true) {
				Account info = (Account)in.readObject();
				if(info==null) {
					break;
				}
				accountArr.add(info);
			}
		}
		catch(Exception e) {
			System.out.println("AccountInfo.obj 복원완료");
		}
	}

	//컬렉션에 남아있는 정보를 파일로 저장한다.
	public void saveFile() {
		try {
			FileOutputStream file = new FileOutputStream(dataFile);
			ObjectOutputStream out = new ObjectOutputStream(file);

			Iterator<Account> itr = accountArr.iterator();
			while(itr.hasNext()) {
				/*
				반복자를 통해 컬렉션에 저장된 모든 인스턴스를
				writeObject메소드를 통해 파일에 저장한다.
				*/
				out.writeObject(itr.next());
			}

			out.close();
			System.out.println("AccountInfo.obj 파일로 저장되었습니다.");
		}
		catch(IOException e) {
			System.out.println("AccountInfo.obj 저장중 예외발생");
		}
	}
	
	public void dataSaveOption(AutoSaver sa) {
		System.out.println("저장옵션을 선택하세요.");
		System.out.print("1.자동저장On, 2.자동저장Off\n선택:");
		int menu = sc.nextInt();
		if(menu==1) {			
			//텍스트로 저장되는지 테스트
			//saveInfoTxt();
			
			//자동저장 쓰레드 start		
			if(!sa.isAlive()) {//쓰레드가 살아있는지 확인							
				sa.setDaemon(true);
				sa.start();
				System.out.println("자동저장을 시작합니다.");
			}
			else {
				System.out.println("이미 자동저장이 실행중입니다.");
			}
		}
		else if(menu==2) {
			//자동저장 쓰레드 interrupt
			if(sa.isAlive()) {
				sa.interrupt();
				System.out.println("자동저장을 종료합니다.");
			}
		}
		else {
			System.out.println("메뉴를 잘못입력하셨습니다.");
		}
	}
	
	public void saveInfoTxt() {	
		try {
			PrintWriter out = new PrintWriter(new FileWriter("src/banking6/AutoSaveAccount.txt"));
			
			for(Account p : accountArr) {
				out.println(p);
			}
			
			out.close();
			System.out.println("계좌정보가 텍스트로 자동저장되었습니다.");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
