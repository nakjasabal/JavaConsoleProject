package salary.management02;

public class PermanentWorker extends Employee {
	/*
	정규직 : 기본급을 받는다. 
	 */
	int salary;
	public PermanentWorker(String name, String department, int salary) {
		super(name, department);
		this.salary = salary;
	}
	@Override
	public double getPay() {
		//기본 급여만 받는다. 
		return salary;
	}
	@Override
	public String toString() {		
		return super.toString() + String.format(", 기본급:%d", this.salary);
	}
}