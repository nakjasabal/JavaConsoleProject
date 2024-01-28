package salary.management01;

import java.util.Scanner;

public interface IMenu {
	/*
	아래 메뉴를 상수 형태로 선언해보자.
	1.입력
	2.검색
	3.삭제
	4.사원정보출력
	5.직원별급여
	6.급여총합
	7.프로그램종료

	interface에서 변수를 선언하게 되면 자동으로
	public static final 이 된다.

	아래 상수선언을 통해 메뉴선택에 대한 코드가 명확해지게된다.
	*/

	int ADD=1, SEARCH=2, DELETE=3, SHOW=4, 
			TOTAL_SALARY=5, DEPARTMENT_SALARY=6, EXIT=7;
	Scanner scanner = new Scanner(System.in);
}
