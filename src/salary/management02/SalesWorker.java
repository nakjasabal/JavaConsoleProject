package salary.management02;

public class SalesWorker extends PermanentWorker {
	/*
	영업직 : 정규직이면서 영업결과에 따른 성과급을 받는다. 
	 */
	int salesResult;
	double bonusRatio;
	
	public SalesWorker(String name, String department, int salary, int salesResult, double bonusRatio) {
		super(name, department, salary);
		this.salesResult = salesResult;
		this.bonusRatio = bonusRatio;
	}
	@Override
	public double getPay() {
		//기본급여 + (영업결과*보너스율)
		double result = super.getPay() + (salesResult * (bonusRatio/100));
		return result;
	}
	@Override
	public String toString() {		
		return super.toString() + String.format(", 영업실적:%d, 보너스율:%f", this.salesResult, this.bonusRatio);
	}
}