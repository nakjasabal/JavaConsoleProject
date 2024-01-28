package project1a.ver08;

import java.util.Scanner;

public class PhoneBookVer08 {

	public static void main(String[] args) {

		//핸들러 클래스 객체생성
		PhoneBookManager pm = new PhoneBookManager();
		AutoSaver as = null;

		while(true) {

			//선택할 메뉴를 출력한다.
			pm.printMenu();

			int iMenu = PhoneBookManager.sc.nextInt();
			PhoneBookManager.sc.nextLine();//버퍼날림

			if(iMenu==MenuItem.INPUT) {
				System.out.println("==주소록을 입력함==");
				pm.dataInput();
			}
			else if(iMenu==MenuItem.SEARCH) {
				System.out.println("==주소록을 검색함==");
				pm.dataSearch();
			}
			else if(iMenu==MenuItem.DELETE) {
				System.out.println("==주소록을 삭제함==");
				pm.dataDelete();
			}
			else if(iMenu==MenuItem.PRINT) {
				System.out.println("==주소록을 출력함==");
				pm.dataAllShow();
			}
			else if(iMenu==MenuItem.SAVEOPTION) {
				System.out.println("==저장옵션선택==");		
				try {
					if(!as.isAlive()) {
						as = new AutoSaver(pm);
					}
				}
				catch (Exception e) {
					System.out.println("AutoSaver예외발생");
					as = new AutoSaver(pm);
				}
				System.out.println("쓰레드="+ as);
				pm.dataSaveOption(as);
			}
			else if(iMenu==MenuItem.EXIT) {

				//프로그램 종료시 컬렉션에 저장된
				//주소록정보 파일로 저장함.
				pm.saveFile();

				//프로그램을 종료함
				System.out.println("프로그램을 종료합니다.");
				//System.exit(0);
				break;
			}
			else {
				System.out.println("==선택오류==");
				System.out.println("1~5 중 하나를 선택하삼");
			}
		}

	}//end of main

}//end of class








