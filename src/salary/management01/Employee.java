package salary.management01;

import java.io.Serializable;
import java.util.Objects;

public abstract class Employee implements Serializable {
	//이름
	private String name;
	//부서
	private String department;
	//생성자
	public Employee(String name, String department) {
		this.name = name;
		this.department = department;
	}	
	public String getName() {
		return name;
	}
	public String getDepartment() {
		return department;
	}
	@Override
	public String toString() {		
		return String.format("이름:%s, 부서:%s", this.name, this.department);
	}
	@Override
	public int hashCode() {		
		return Objects.hash(name, department);
	}
	@Override
	public boolean equals(Object obj) {
		Employee emp = (Employee)obj;
		if(emp.name.equals(this.name) && emp.department.equals(this.department)) {
			return true;
		}
		else {
			return false;
		}
	}
	//내 급여 계산
	public abstract double getPay();
}