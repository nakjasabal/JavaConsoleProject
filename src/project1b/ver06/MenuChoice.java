package project1b.ver06;

//메뉴선택을 위한 인터페이스형 상수 선언
public interface MenuChoice {
	/*
	1계좌개설
	2입금
	3출금
	4전체계좌정보출력
	5프로그램종료
	*/
	int MAKE=1, DEPOSIT=2, WITHDRAW=3, INQUIRE=4, 
			THREE_BY_3=5, AUTO_SAVE=6, EXIT=7;
	/*
	interface내에 선언되는 변수는
		무조건 public static final로 선언된다.
	*/
}
