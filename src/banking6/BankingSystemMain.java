package banking6;

import java.util.InputMismatchException;
import java.util.Scanner;

import threeby3.Puzzle3by3;
import threeby3.Xposition;
 
public class BankingSystemMain {

	public static void main(String[] args) {

		//AccountManager 인스턴스 생성
		AccountManager ac = new AccountManager();
		//인스턴스 생성 후 저장된 파일을 읽어와서 역직열화한다. 
		ac.readFile();
		
		AutoSaver as = null;

		while(true) {

			//메뉴출력
			ac.showMenu();

			try {
				//메뉴입력
				int keyInput = ac.sc.nextInt();
				ac.sc.nextLine();//버퍼날림

				if(keyInput==MenuChoice.MAKE) {
					System.out.println("***계좌개설***");
					ac.makeAccount();
				}
				else if(keyInput==MenuChoice.DEPOSIT) {
					System.out.println("***입 금***");
					ac.depositMoney();
				}
				else if(keyInput==MenuChoice.WITHDRAW) {
					System.out.println("***출 금***");
					ac.withdrawMoney();
				}
				else if(keyInput==MenuChoice.INQUIRE) {
					System.out.println("***계좌정보출력***");
					ac.showAccInfo();
				}				
				else if(keyInput==MenuChoice.DELETE) {
					System.out.println("***계좌정보삭제***");
					ac.deleteAccount();
				}
				else if(keyInput==MenuChoice.AUTO_SAVE) {//자동저장
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
				else if(keyInput==MenuChoice.GAME) {
					gameStart();
				}
				else if(keyInput==MenuChoice.EXIT) {
					//프로그램 종료시 컬렉션에 저장된 계좌정보를 파일에 저장함.
					ac.saveFile();
					
					System.out.println("***프로그램종료***");					
					System.exit(0);
				}
				else {
					//메뉴이외의 다른 숫자를 입력했을때
					//사용자정의 예외를 발생시킨다.
					MenuSelectException ex =
						new MenuSelectException();
					throw ex;
				}
			}
			catch(MenuSelectException e) {
				System.out.println(e.getMessage());
				System.out.println("메뉴는 1~5사이의 "
						+ "정수를 입력하세요");
			}
			catch(InputMismatchException e) {
				System.out.println("입력은 숫자로만 하세요.");
				ac.sc.nextLine();//버퍼날림
			}
		}
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
						System.out.println("Game이 종료되었습니다.");
						//System.exit(0);
						break;//루프탈출
					}					
				}
			}
		}		
	}
}









