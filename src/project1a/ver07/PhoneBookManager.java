package project1a.ver07;

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


	//메뉴를 출력하기 위한 메소드 정의
	public void printMenu() {
		System.out.println("메뉴를 선택하세요");
		System.out.println("1.주소록입력");
		System.out.println("2.검색");
		System.out.println("3.삭제");
		System.out.println("4.출력");
		System.out.println("5.프로그램종료");
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

		System.out.print("검색할 이름을 입력하세요:");
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

}














