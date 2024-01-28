package salary.management01;

 
/*
상속구조 : 
 */
//사원기본
/*
사원은 이름과 부서가 일치하면 같은 객체로 취급하여 저장하지 않는다. 
저장되지 않는다는 메세지만 보여주자.  

차후 덮어쓸지 물어본 후 덮어쓰기를 하겠다면 기존의 객체는 삭제한 후 추가한다. 
이 부분은 별도로 리뷰해주는걸로 한다. 어렵다.  
 */
public class EmployeeSalaryMain {

	public static void main(String[] args) {

		//핸들러 클래스 객체생성
		SalaryManager manager = new SalaryManager();
		manager.readFile();		
		
		while(true) {
			//선택할 메뉴를 출력한다.
			manager.printMenu();

			try {
				int iMenu = IMenu.scanner.nextInt();
				IMenu.scanner.nextLine();
	
				if(iMenu==IMenu.ADD) {//사원추가
					manager.addEmployee();
				}
				else if(iMenu==IMenu.SEARCH) {//사원검색
					manager.searchEmployee();
				}
				else if(iMenu==IMenu.DELETE) {//사원삭제
					manager.deleteEmployee();
				}
				else if(iMenu==IMenu.SHOW) {//전체사원정보출력
					manager.showEmployeeInfo();
				}
				else if(iMenu==IMenu.TOTAL_SALARY) {//지급할 급여 총액
					manager.showTotalSalary();
				}
				else if(iMenu==IMenu.DEPARTMENT_SALARY) {//부서별 급여 총액
					manager.showTotalDepartmentSalary();
				}
				else if(iMenu==IMenu.EXIT) {//프로그램 종료
					System.out.println("프로그램을 종료합니다.");
					manager.saveFile();
					break;
				}
				else {
					System.out.println("메뉴 선택을 잘못하셨습니다.");
				}
			}
			catch (Exception e) {
				System.out.println("다시 입력해주세요.");
				IMenu.scanner.nextLine();//버퍼날림
			}
		}
	}
}

