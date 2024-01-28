package salary.management01;

public class TemporaryWorker extends Employee {
	/*
	임시직(알바) : 시간제 급여를 받는다. 
	 */
	int workTime;
	int payPerHours;
	
	public TemporaryWorker(String name, String department, int workTime, int payPerHours) {
		super(name, department);
		this.workTime = workTime;
		this.payPerHours = payPerHours;
	}
	@Override
	public double getPay() {
		//시간 * 시급
		return workTime * payPerHours;
	}
	@Override
	public String toString() {		
		return super.toString() + String.format(", 근무시간:%d, 시급:%d", this.workTime, this.payPerHours);
	}
}