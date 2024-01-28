package project1a.ver04;

import java.util.Scanner;

public class PhoneBookManager {

	//키보드를 통한 입력을 위한 객체생성
	/*
	static형태로 선언하였으므로 별도의 객체생성없이 사용이
	가능하며, 클래스 전체 영역에서 접근이 가능하다.
	즉, 각 지역마다 객체를 생성할 필요가 없다.
	 */
	public static Scanner sc = new Scanner(System.in);


	//입력받아 생성한 PhoneInfo객체를 저장할 객체배열 생성
	PhoneInfo[] addrArray = new PhoneInfo[100];
	int arrayCount = 0;//생성된 객체의 갯수를 카운트할 변수


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
		case 1://일반 연락처

			phoneInfo = new PhoneInfo(name, tel);
			break;
		case 2://학교친구 연락처
			System.out.print("전공:");
			String major = sc.nextLine();
			System.out.print("학년:");
			int year = sc.nextInt();

			phoneInfo = new PhoneSchoolInfo(name, tel, major, year);
			break;
		case 3://직장동료 연락처
			System.out.print("직장명:");
			String company = sc.nextLine();

			phoneInfo = new PhoneCompanyInfo(name, tel, company);
			break;
		default:
		}

		addrArray[arrayCount++] = phoneInfo;
		System.out.println("===입력이완료되었습니다===\n");

	}//end of dataInput

	//검색 :
	public void dataSearch() {

		boolean isSearch = false;//검색결과 여부 판단

		System.out.print("검색할 이름을 입력하삼:");
		String searchName = sc.nextLine();

		for(int i=0 ; i<arrayCount ; i++) {
			if(searchName.compareTo(addrArray[i].getName())==0) {
				addrArray[i].showPhoneInfo();
				isSearch = true;
			}
		}

		if(isSearch==false)
			System.out.println("==검색결과없음==");
	}
	//삭제 :
	public void dataDelete() {

		System.out.print("삭제할 이름을 입력하세요:");
		String deleteName = sc.nextLine();

		int deleteIndex = -1;//배열의 인덱스는 -1이 없으므로
							//초기값은 -1로 설정

		for(int i=0 ; i<arrayCount ; i++) {
			//삭제할 이름을 객체배열에서 찾았다면...
			if(deleteName.compareTo(addrArray[i].getName())==0) {

				//배열안에서 참조변수를 null값으로 변경함
				addrArray[i] = null;//반드시 해야할 필요없음.

				//삭제된 참조변수의 인덱스값을 저장
				deleteIndex = i;

				//전체 주소록의 갯수 -1
				arrayCount --;
			}
		}

		if(deleteIndex==-1) {
			System.out.println("==삭제된 데이터가 없습니다==");
		}
		else {
			//객체배열에서 삭제된 인덱스를 뒤쪽의 요소들로 채움.
			for(int i=deleteIndex ; i<arrayCount ; i++	) {
				addrArray[i] = addrArray[i+1];
			}
			System.out.println("==삭제가 완료되었습니다==");
		}
	}

	//주소록전체출력 :
	public void dataAllShow() {

		for(int i=0 ; i<arrayCount ; i++) {

			/*
			i번째 인덱스에 저장된 PhoneInfo객체의 참조변수를
			통해 showPhoneInfo() 멤버메소드를 호출하여
			해당 정보를 출력한다.
			 */
			addrArray[i].showPhoneInfo();
			System.out.println("=====================");
		}

		System.out.println("===주소록 출력이 완료되었습니다===\n");

	}//end of dataAllShow()

}














