package threeby3;

import java.util.Random;
import java.util.Scanner;

public class Puzzle3by3 {
	
	Scanner scanner = new Scanner(System.in);
	Random random = new Random();
	public static int SHUFFLE_COUNT = 2;
	
	//생성자
	public Puzzle3by3() {}
	
	//출력용
	public void showPuzzle(String[][] param) {
		System.out.println("=========");
		for(int i=0 ; i<param.length ; i++) {
			for(int j=0 ; j<param[i].length ; j++) {
				System.out.print(param[i][j]+" ");
			}	
			System.out.println();
		}	
		System.out.println("=========");
	}
	//이동불가
	public void doNotMove() {
		System.out.println("xxxxxxxxxxxxxxxxxx");				
		System.out.println("xxxxx이동불가xxxxx");				
		System.out.println("xxxxxxxxxxxxxxxxxx");	
	}
	//메뉴출력
	public String menuShow(String[][] paramPuz) {
		System.out.println();
		//메뉴를 보여주기 전에 항상 퍼즐을 디스플레이한다. 
		showPuzzle(paramPuz);		
		System.out.println("[ 이동 ] a:Left d:Right w:Up s:Down");
		System.out.println("[ 종료 ] x:Exit");
		System.out.printf("키를 입력해주세요 : ");
		return scanner.nextLine();
	}
	//재시작여부
	public boolean rePlayConfirm() {
		System.out.printf("재시작하시겠습니까?(y 누르면 재시작, 나머지는 종료)");
		String input = scanner.nextLine();
		if(input.equalsIgnoreCase("y")) {
			return true;
		}
		else {
			//System.out.println("Game이 종료되었습니다.");
			//System.exit(0);
			return false;
		}
	}
	//블록 셔플 - 셔플할 배열과 x의 위치값을 매개변수로 받는다. 
	public String[][] shufflePuzzle(String[][] shufflePuz, Xposition xpos) {	
		/*
		난수를 통해  a,d,w,s를 발생시켜 shiftBlock()메소드 호출하기
		
		셔플용은 이동불가 메세지 띄우지 않음
		 */
		for(int i=1 ; i<=SHUFFLE_COUNT ; i++) {
			String[] button = {"w","s","a","d"};
			int rndNum = random.nextInt(10000) % 4 ;
			shiftBlock(shufflePuz, xpos, button[rndNum], 0);//0이면 이동불가 메세지 띄우지 않음
		}				
		
		return shufflePuz;
	}
	//블록 이동 => 
	public void shiftBlock(String[][] sPuz, Xposition xpos, String iKey, int noMsg) {
				
		String xtemp;
		
		if(iKey.equalsIgnoreCase("a")) {
			//좌로이동 => x는 우측으로 이동
						
			//배열의 크기를 벗어나면 이동불가
			if((xpos.horizontal+1) >= 3) {
				if(noMsg==1) 
					doNotMove(); 		
				return;
			}			

			//문제없다면 교환
			xtemp = sPuz[xpos.vertical][xpos.horizontal];
			sPuz[xpos.vertical][xpos.horizontal] = sPuz[xpos.vertical][xpos.horizontal+1];
			sPuz[xpos.vertical][xpos.horizontal+1] = xtemp;
			//showPuzzle(sPuz);
			
			//가로 인덱스 수정(++)
			xpos.horizontal++;
			//xpos.showXPosition();
		}
		else if(iKey.equalsIgnoreCase("d")) {
			//우로이동 => x는 좌측으로 이동
			
			//배열의 크기를 벗어나면 이동불가
			if((xpos.horizontal-1) < 0) {
				if(noMsg==1) 
					doNotMove(); 	
				return;
			}			

			//문제없다면 교환
			xtemp = sPuz[xpos.vertical][xpos.horizontal];
			sPuz[xpos.vertical][xpos.horizontal] = sPuz[xpos.vertical][xpos.horizontal-1];
			sPuz[xpos.vertical][xpos.horizontal-1] = xtemp;
			//showPuzzle(sPuz);
			
			//가로 인덱스 수정(--)
			xpos.horizontal--;
			//xpos.showXPosition();			
		}
		else if(iKey.equalsIgnoreCase("w")) {
			//위로이동 => x는 아래로 이동
			
			//배열의 크기를 벗어나면 이동불가
			if((xpos.vertical+1) >=3) {
				if(noMsg==1) 
					doNotMove(); 	
				return;
			}			

			//문제없다면 교환
			xtemp = sPuz[xpos.vertical][xpos.horizontal];
			sPuz[xpos.vertical][xpos.horizontal] = sPuz[xpos.vertical+1][xpos.horizontal];
			sPuz[xpos.vertical+1][xpos.horizontal] = xtemp;
			//showPuzzle(sPuz);
			
			//세로 인덱스 수정(++)
			xpos.vertical++;
			//xpos.showXPosition();
		}
		else if(iKey.equalsIgnoreCase("s")) {
			//아래로이동 => x는 위로 이동
			
			//배열의 크기를 벗어나면 이동불가
			if((xpos.vertical-1) < 0) {
				if(noMsg==1) 
					doNotMove(); 	
				return;
			}			

			//문제없다면 교환
			xtemp = sPuz[xpos.vertical][xpos.horizontal];
			sPuz[xpos.vertical][xpos.horizontal] = sPuz[xpos.vertical-1][xpos.horizontal];
			sPuz[xpos.vertical-1][xpos.horizontal] = xtemp;
			//showPuzzle(sPuz);
			
			//세로 인덱스 수정(--)
			xpos.vertical--;
			//xpos.showXPosition();
		}
		else {
			//키입력실패
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");				
			System.out.println("xxxxxxxxxxxxx키입력실패xxxxxxxxxxxxx");
			System.out.println("[ 이동 ] a:Left d:Right w:Up s:Down");
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		}		
	}	 
	
	//정답인지 확인
	public boolean answerConfirm(String[][] shufflePuz, String[][] sourcePuz) {
		
		boolean confirmFlag = true;
		
		//다른게 발견되면 false반환
		for(int i=0 ; i<shufflePuz.length ; i++) {
			for(int j=0 ; j<shufflePuz[i].length ; j++) {
				if(!shufflePuz[i][j].equals(sourcePuz[i][j])) {
					confirmFlag = false;
				}
			}
		}	
		
		return confirmFlag;
	}
}