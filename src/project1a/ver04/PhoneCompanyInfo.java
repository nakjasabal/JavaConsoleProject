package project1a.ver04;

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
		super.showPhoneInfo();
		System.out.println("직장명:"+ company);
	}
}


