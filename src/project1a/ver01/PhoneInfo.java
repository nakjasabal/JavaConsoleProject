package project1a.ver01;

public class PhoneInfo {

	//멤버변수
	String name;
	String phoneNumber;
	String birthday;

	//매개변수 2개인 생성자
	public PhoneInfo(String n, String p) {
		/*name = n;
		phoneNumber = p;*/

		this(n, p, null);
	}
	//매개변수 3개인 생성자
	public PhoneInfo(String n, String p, String b) {
		name = n;
		phoneNumber = p;
		birthday = b;
	}
	//멤버변수 출력을 위한 메소드
	public void showPhoneInfo() {
		System.out.println("이름:"+ name);
		System.out.println("전화번호:"+ phoneNumber);
		if(birthday==null) {
			System.out.println("생년월일:입력되지않음");
		}
		else {
			System.out.println("생년월일:"+ birthday);
		}
	}
}






