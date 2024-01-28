package project1a.ver08;

/*
직장동료를 표현한 클래스 : 부모클래스(이름, 전화번호) + 직장명
 */
public class PhoneCompanyInfo extends PhoneInfo {

	//확장한 멤버변수
	private String company;//회사명

	public PhoneCompanyInfo(String name, String phoneNumber,
			String company) {
		super(name, phoneNumber);//부모 생성자 호출
		this.company = company;
	}
	@Override
	public void showPhoneInfo() {
		//super.showPhoneInfo();
		System.out.print("이름:"+ getName());
		System.out.print(", 전화번호:"+ getPhoneNumber());
		System.out.print(", 직장명:"+ company +"\n");
	}
	@Override
	public String toString() {		
		return String.format("이름:%s, 전화번호:%s, 직장명:%s%n", 
				getName(), getPhoneNumber(), company);
	}
}


