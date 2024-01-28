package salary.management01;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Iterator;


public class SalaryManager {
		
	HashSet<Employee> employees;
	private final File dataFile = new File("src/test/EmployeesInfo.obj");
	
	public SalaryManager() {
		employees = new HashSet<Employee>();
	}
	
	public void printMenu() {
		System.out.println("===============메뉴를 선택하세요===============");
		System.out.print("1.사원추가 ");
		System.out.print("2.검색 ");
		System.out.print("3.삭제 ");
		System.out.println("4.사원정보출력 ");
		System.out.print("5.직원별급여 ");
		System.out.print("6.급여총합 ");
		System.out.println("7.프로그램종료");
		System.out.println("===============================================");
		System.out.print("메뉴선택:");
	}
	
	//사원추가
	/*
	공통정보 : 이름, 부서
	1.정규직 : 기본급		
	2.영업직 : 기본급, 영업달성금액, 보너스율	
	3.알바 : 시급, 근무시간
	 */
	public void addEmployee() {
		System.out.println("사원정보를 입력합니다.");
		System.out.println("1.정규직 2.영업직 3.임시직(알바)");
		System.out.print("선택:");
		int empChoice = IMenu.scanner.nextInt();
		IMenu.scanner.nextLine();

		try {
			if(!(empChoice==1 || empChoice==2 || empChoice==3)) {
				ChoiceException ce = new ChoiceException();
				throw ce;
			}
			else {
				System.out.print("이름:");
				String name = IMenu.scanner.nextLine();
				System.out.print("부서:");
				String dept = IMenu.scanner.nextLine();
				
				Employee employee = null;
				if(empChoice==1) {
					System.out.print("기본급:");
					int salary = IMenu.scanner.nextInt();
					
					employee = new PermanentWorker(name, dept, salary);
				}
				else if(empChoice==2) {
					System.out.print("기본급(정수):");
					int salary = IMenu.scanner.nextInt();
					System.out.print("영업실적(정수):");
					int sales = IMenu.scanner.nextInt();
					System.out.print("보너스율(정수):");
					int bonus = IMenu.scanner.nextInt();
					
					employee = new SalesWorker(name, dept, salary, sales, bonus);
				}
				else if(empChoice==3) {
					System.out.print("근무시간(정수):");
					int time = IMenu.scanner.nextInt();
					System.out.print("시급(정수):");
					int hour = IMenu.scanner.nextInt();
					
					employee = new TemporaryWorker(name, dept, time, hour);
				}
				boolean isAdd = employees.add(employee);
				if(isAdd==true)
					System.out.println("\n사원정보가 입력되었습니다.\n");
				else
					System.out.println("\n중복된 사원이 있어 입력되지 않았습니다.\n");		
			}
		}
		catch (Exception e) {
			System.out.println("[예외발생]입력할 직원을 다시 선택하세요.");
		}			
	}
	//사원검색	
	public void searchEmployee() {
		boolean isSearch = false;//검색결과 여부 판단
		System.out.print("검색할 이름을 입력하세요:");
		String searchName = IMenu.scanner.nextLine();

		//반복자를 통한 컬렉션 반복하기
		Iterator<Employee> itr = employees.iterator();
		while(itr.hasNext()) {
			Employee currentInfo = itr.next();
			if(searchName.compareTo(currentInfo.getName())==0) {
				System.out.println(currentInfo); 
				isSearch = true;
			}
		}
		if(isSearch==false)
			System.out.println("\n검색결과가 없습니다.\n");
	}
	//사원삭제
	public void deleteEmployee() {
		boolean isDelete = false;//삭제 여부 판단
		System.out.print("삭제할 이름을 입력하세요:");
		String deleteName = IMenu.scanner.nextLine();
		
		for(Employee e : employees) {
			if(deleteName.equals(e.getName())) {
				employees.remove(e); 
				isDelete = true;
				System.out.println("\n사원정보가 삭제되었습니다.\n");
			}
		}
		if(isDelete==false)
			System.out.println("\n일치하는 정보가 없습니다.\n");
	}
	//전체사원의 정보 출력
	public void showEmployeeInfo() {
		for(Employee e : employees) {
			System.out.println(e);
		}
	}
	//지급해야 할 각 직원의 급여출력 
	public void showTotalSalary() {
		for(Employee e : employees) {
			String str = String.format("%s 사원의 급여:%.0f", e.getName(), e.getPay());
			System.out.println(str);
		}
	}	
	//급여의 총합
	public void showTotalDepartmentSalary() {
		double totalSalary = 0;
		for(Employee e : employees) {
			totalSalary += e.getPay();
		}
		System.out.println("급여의총합:"+ totalSalary);
	} 
	//컬렉션에 남아있는 정보를 파일로 저장한다.
	public void saveFile() {
		try {
			FileOutputStream file = new FileOutputStream(dataFile);
			ObjectOutputStream out = new ObjectOutputStream(file);

			Iterator<Employee> itr = employees.iterator();
			while(itr.hasNext()) {
				out.writeObject(itr.next());
			}

			out.close();
			System.out.println("obj 파일로 저장되었습니다.");
		}
		catch(IOException e) {
			System.out.println("사원정보 직렬화 중 예외발생");
		}
	}
	//폰북이 저장된 파일을 읽는다.
	public void readFile() {

		//만약 해당경로에 파일이 없다면 실행중지
		if(dataFile.exists()==false) {
			System.out.println("직렬화 된 파일없음");
			return;
		}

		//파일이 존재한다면 읽어서 컬렉션에 저장한다.
		try {
			FileInputStream file = new FileInputStream(dataFile);
			ObjectInputStream in = new ObjectInputStream(file);
			while(true) {
				Employee info = (Employee)in.readObject();
				if(info==null) {
					break;
				}
				employees.add(info);
			}
		}
		catch(Exception e) {}
	}
}
