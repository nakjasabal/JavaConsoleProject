package project1b.ver06;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

import project1b.ver04.threeby3.Puzzle3by3;
import project1b.ver04.threeby3.Xposition;

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


	//메뉴출력
	public void showMenu() {
		System.out.println("==================Menu==================");
		System.out.print("1.계좌개설");
		System.out.print(", 2.입금");
		System.out.print(", 3.출금");
		System.out.println(", 4.계좌정보출력");
		System.out.print("5.3By3퍼즐[추가]");
		System.out.print(", 6.저장옵션");
		System.out.println(", 7.종료");
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
	
	//게임시작
	public void gameStart() {
		
		Puzzle3by3 puz = new Puzzle3by3();
		
		//정답용 배열
		String[][] sourcePuz = { { "1", "2", "3" }, { "4", "5", "6" }, { "7", "8", "x" } };
		//puz.showPuzzle(sourcePuz);		
		//정답용 배열 x의 위치
		Xposition xposAnswer = new Xposition(2,2);//세로/가로
		//xposAnswer.showXPosition();
		
				
		//셔플용 배열
		String[][] shufflePuz = { { "1", "2", "3" }, { "4", "5", "6" }, { "7", "x", "8" } };
		//puz.showPuzzle(shufflePuz);		
		//셔플용 배열 x의 위치
		Xposition xposShuffle = new Xposition(2,1);//세로/가로
		//xposShuffle.showXPosition();
		
		/*셔플은 항상 정답용 배열을 사용한다. 임의난수를 통해 셔플하는경우 풀리지 않는 케이스가 
		발생할 수 있다.*/
		puz.shufflePuzzle(shufflePuz, xposShuffle);//셔플용은 이동불가 메세지 띄우지 않음	
				
		while(true) {
					
			//메뉴출력 및 입력
			String iKey = puz.menuShow(shufflePuz);
			
			if(iKey.equalsIgnoreCase("x")) {
				//게임종료
				System.out.println("게임을 종료합니다.");
				break;
			}
			else {
				//상하좌우 이동 => 셔플된퍼즐, 셔플된 x의 위치, 이동방향
				puz.shiftBlock(shufflePuz, xposShuffle, iKey, 1);//1이면 이동불가 메세지 띄움
				
				//이동후 정답인지 확인한다. 
				boolean aConfirm = puz.answerConfirm(shufflePuz, sourcePuz);
				if(aConfirm==true) {
					System.out.println("==^^정답입니다^^==");
					puz.showPuzzle(shufflePuz);
					if(puz.rePlayConfirm()==true) {
						//재시작
						puz.shufflePuzzle(shufflePuz, xposShuffle);//셔플용은 이동불가 메세지 띄우지 않음	
					}
					else {
						//게임종료
						System.out.println("종료되었습니다.");
						//System.exit(0);
						break;//루프탈출
					}					
				}
			}
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
			PrintWriter out = new PrintWriter(new FileWriter("src/project1b/ver06/AutoSaveAccount.txt"));
			
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
