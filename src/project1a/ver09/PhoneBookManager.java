package project1a.ver09;

import java.util.Scanner;

public class PhoneBookManager {

	//키보드를 통한 입력을 위한 객체생성
	/*
	static형태로 선언하였으므로 별도의 객체생성없이 사용이
	가능하며, 클래스 전체 영역에서 접근이 가능하다.
	즉, 각 지역마다 객체를 생성할 필요가 없다.
	 */
	public static Scanner sc = new Scanner(System.in);
	PhoneBookDAO dao = new PhoneBookDAO();


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
		System.out.print("이름:");
		String name = sc.nextLine();
		System.out.print("전화번호:");
		String tel = sc.nextLine();
		System.out.print("생년월일:");
		String birth = sc.nextLine();

		//입력받은 정보를 통해 새로운 객체 생성
		PhoneInfo phoneInfo =
				new PhoneInfo(name, tel, birth);

		//addrArray[arrayCount++] = phoneInfo;
		dao.dataInput(phoneInfo);
		System.out.println("===입력이완료되었습니다===\n");

	}//end of dataInput

	//검색 :
	public void dataSearch() {

		boolean isSearch = false;//검색결과 여부 판단

		System.out.print("검색할 이름을 입력하삼:");
		String searchName = sc.nextLine();

//		for(int i=0 ; i<arrayCount ; i++) {
//			if(searchName.compareTo(addrArray[i].getName())==0) {
//				addrArray[i].showPhoneInfo();
//				isSearch = true;
//			}
//		}
		isSearch = dao.dataSearch(searchName);

		if(isSearch==false)
			System.out.println("==검색결과없음==");
	}
	//삭제 :
	public void dataDelete() {

		System.out.print("삭제할 이름을 입력하세요:");
		String deleteName = sc.nextLine();

		int deleteIndex = 0;

//		for(int i=0 ; i<arrayCount ; i++) {
//			//삭제할 이름을 객체배열에서 찾았다면...
//			if(deleteName.compareTo(addrArray[i].getName())==0) {
//
//				//배열안에서 참조변수를 null값으로 변경함
//				addrArray[i] = null;//반드시 해야할 필요없음.
//
//				//삭제된 참조변수의 인덱스값을 저장
//				deleteIndex = i;
//
//				//전체 주소록의 갯수 -1
//				arrayCount --;
//			}
//		}
		deleteIndex = dao.dataDelete(deleteName);

		if(deleteIndex==0) {
			System.out.println("==삭제된 데이터가 없습니다==");
		}
		else {
			//객체배열에서 삭제된 인덱스를 뒤쪽의 요소들로 채움.
//			for(int i=deleteIndex ; i<arrayCount ; i++	) {
//				addrArray[i] = addrArray[i+1];
//			}
			System.out.println("==삭제가 완료되었습니다==");
		}
	}

	//주소록전체출력 :
	public void dataAllShow() {

//		for(int i=0 ; i<arrayCount ; i++) {
//
//			/*
//			i번째 인덱스에 저장된 PhoneInfo객체의 참조변수를
//			통해 showPhoneInfo() 멤버메소드를 호출하여
//			해당 정보를 출력한다.
//			 */
//			addrArray[i].showPhoneInfo();
//			System.out.println("=====================");
//		}
		dao.dataAllShow();

		System.out.println("===주소록 출력이 완료되었습니다===\n");

	}//end of dataAllShow()

}














