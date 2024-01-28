package project1a.ver08;

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

public class PhoneBookManager {

	//키보드를 통한 입력을 위한 객체생성
	/*
	static형태로 선언하였으므로 별도의 객체생성없이 사용이
	가능하며, 클래스 전체 영역에서 접근이 가능하다.
	즉, 각 지역마다 객체를 생성할 필요가 없다.
	 */
	public static Scanner sc = new Scanner(System.in);


	//객체를 저장할 HashSet<E>컬렉션을 선언
	HashSet<PhoneInfo> addrArray = new HashSet<PhoneInfo>();


	//8단계 : I/O스트림을 통한 직렬화
	private final File dataFile =
		new File("src/project1a/ver08/PhoneBook.obj");


	//기본생성자
	public PhoneBookManager() {
		/*
		프로그램이 실행된후 PhoneBookManager의 객체가
		생성될때 readFile()메소드를 호출하여
		PhoneBook.obj파일을 읽어 객체화 한다.
		 */
		readFile();
	}

	//폰북이 저장된 파일을 읽는다.
	public void readFile() {

		//만약 해당경로에 파일이 없다면 실행중지
		if(dataFile.exists()==false) {
			System.out.println("PhoneBook.obj 파일없음");
			return;
		}

		//파일이 존재한다면 읽어서 컬렉션에 저장한다.
		try {
			FileInputStream file =
				new FileInputStream(dataFile);
			ObjectInputStream in =
				new ObjectInputStream(file);
			while(true) {
				PhoneInfo info =
					(PhoneInfo)in.readObject();
				if(info==null) {
					break;
				}
				addrArray.add(info);
			}
		}
		catch(Exception e) {}
	}

	//컬렉션에 남아있는 정보를 파일로 저장한다.
	public void saveFile() {
		try {
			FileOutputStream file = new FileOutputStream(dataFile);
			ObjectOutputStream out = new ObjectOutputStream(file);

			Iterator<PhoneInfo> itr = addrArray.iterator();
			while(itr.hasNext()) {
				/*
				반복자를 통해 컬렉션에 저장된 모든 인스턴스를
				writeObject메소드를 통해 파일에 저장한다.
				*/
				out.writeObject(itr.next());
			}

			out.close();
			System.out.println("obj 파일로 저장되었습니다.");
		}
		catch(IOException e) {
			System.out.println("PhoneBook.obj 저장중 예외발생");
		}
	}



	//메뉴를 출력하기 위한 메소드 정의	
	public void printMenu() {
		System.out.println("===============메뉴를 선택하세요===============");
		System.out.print("1.주소록입력 ");
		System.out.print("2.검색 ");
		System.out.print("3.삭제 ");
		System.out.print("4.출력 ");
		System.out.print("5.저장옵션 ");
		System.out.println("6.종료");
		System.out.println("===============================================");
		System.out.print("메뉴선택:");
	}

	//입력
	public void dataInput() {

		//입력받기전 일반,학교,회사 중 하나를 선택
		System.out.println("데이터 입력을 시작합니다.");
		System.out.println("1.일반 2.동창 3.직장동료");
		System.out.println("선택>>");
		int menuChoice = sc.nextInt();
		sc.nextLine();//버퍼날림

		System.out.print("이름:");
		String name = sc.nextLine();
		System.out.print("전화번호:");
		String tel = sc.nextLine();

		/*
		부모클래스인 PhoneInfo의 참조변수로 하위클래스의
		인스턴스를 참조할 수 있으므로 객체를 먼저 생성함.
		 */
		PhoneInfo phoneInfo = null;

		switch(menuChoice) {
		case SubMenuItem.NORMAL://일반 연락처

			phoneInfo = new PhoneInfo(name, tel);
			break;
		case SubMenuItem.SCHOOL://학교친구 연락처
			System.out.print("전공:");
			String major = sc.nextLine();
			System.out.print("학년:");
			int year = sc.nextInt();
			sc.nextLine();

			phoneInfo = new PhoneSchoolInfo(name, tel, major, year);
			break;
		case SubMenuItem.COMPANY://직장동료 연락처
			System.out.print("직장명:");
			String company = sc.nextLine();

			phoneInfo = new PhoneCompanyInfo(name, tel, company);
			break;
		default:
		}

		/*
		컬렉션에 저장 : HashSet<E>의 add메소드를 통해 객체를
		 	추가할때 자동으로 equals(), hashCode() 메소드를 통해
		 	객체가 중복저장되는 검사한다. 중복되는 객체가
		 	존재한다면 저장되지 않고 false를 반환한다.
		 */
		boolean isAdded = addrArray.add(phoneInfo);
		if(isAdded==true) {
			System.out.println("===입력이완료되었습니다===\n");
		}
		else {
			//중복된 객체를 덮어쓸지 여부를 물어본후 처리
			System.out.println("이미 저장된 데이터입니다.");
			System.out.println("덮어쓸까요? Y(y) / N(n)");
			String ans = sc.nextLine();
			if(ans.equalsIgnoreCase("Y")) {
				//덮어쓰겠다고 선택했다면...
				addrArray.remove(phoneInfo);//기존 객체를 삭제하고
				addrArray.add(phoneInfo);//새로운 객체를 입력한다.

				System.out.println("입력한 정보가 저장되었습니다");
			}
			else {
				//아니라고 선택했다면...
				//-> 그냥 무시하고 지나간다.
				System.out.println("입력하신 정보는 저장되지 않습니다.");
			}
		}

	}//end of dataInput

	//검색 :
	public void dataSearch() {

		boolean isSearch = false;//검색결과 여부 판단

		System.out.print("검색할 이름을 입력하삼:");
		String searchName = sc.nextLine();

		//반복자를 통한 컬렉션 반복하기
		Iterator<PhoneInfo> itr = addrArray.iterator();
		while(itr.hasNext()) {
			PhoneInfo currentInfo = itr.next();
			if(searchName.compareTo(currentInfo.getName())==0) {
				currentInfo.showPhoneInfo();
				isSearch = true;
			}
		}

		if(isSearch==false)
			System.out.println("==검색결과없음==");
	}

	//삭제 :
	public void dataDelete() {

		boolean isDelete = false;

		System.out.print("삭제할 이름을 입력하세요:");
		String deleteName = sc.nextLine();

		Iterator<PhoneInfo> itr = addrArray.iterator();
		while(itr.hasNext()) {
			PhoneInfo currentInfo = itr.next();
			if(deleteName.compareTo(currentInfo.getName())==0) {
				itr.remove();
				isDelete = true;
			}
		}

		if(isDelete==true) {
			System.out.println("==삭제가 완료되었습니다==");
		}
		else {
			System.out.println("==삭제된 데이터가 없습니다==");
		}
	}

	//주소록전체출력 :
	public void dataAllShow() {

		Iterator<PhoneInfo> itr = addrArray.iterator();
		while(itr.hasNext()) {
			PhoneInfo currentInfo = itr.next();
			currentInfo.showPhoneInfo();
		}

		System.out.println("===주소록 출력이 완료되었습니다===\n");

	}//end of dataAllShow()
	
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
			PrintWriter out = new PrintWriter(new FileWriter("src/project1a/ver08/AutoSaveBook.txt"));
			
			for(PhoneInfo p : addrArray) {
				out.println(p);
			}
			
			out.close();
			System.out.println("주소록이 텍스트로 자동저장되었습니다.");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}














