package project1a.ver07;

public interface MenuItem {
	/*
	아래 메뉴를 상수 형태로 선언해보자.
	1.데이터입력
	2.데이터검색
	3.데이터삭제
	4.출력
	5.프로그램종료

	interface에서 변수를 선언하게 되면 자동으로
	public static final 이 된다.

	아래 상수선언을 통해 메뉴선택에 대한 코드가 명확해지게된다.
	*/

	int INPUT=1, SEARCH=2, DELETE=3, PRINT=4, EXIT=5;
}







