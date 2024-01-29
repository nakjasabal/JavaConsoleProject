package banking8.threeby3;

import java.util.Scanner;

import threeby3.Puzzle3by3;
import threeby3.Xposition;

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
		System.out.println("5.3by3게임시작하기");
		System.out.println("6.프로그램종료");
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
	
	//게임시작
	public static void gameStart() {
		
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
						System.out.println("게임이 종료되었습니다.");
						//System.exit(0);
						break;//루프탈출
					}					
				}
			}
		}		
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
			else if(keyInput==MenuChoice.THREE_BY_3) {//3by3게임
				System.out.println("***3by3게임을 시작합니다***");
				gameStart();					
			}
			else if(keyInput==MenuChoice.EXIT) {
				System.out.println("***프로그램종료***");
				dao.close();
				System.exit(0);
			}
		}
	}
}




