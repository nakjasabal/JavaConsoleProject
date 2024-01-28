package project1a.ver03;

public class PhoneInfo {

	//멤버변수 : 정보은닉을 위해 3단계에서 private으로 선언함.
	/*
	private 선언을 통해 클래스 외부에서의 접근은 원천적으로
	차단되며, 멤버메소드를 통해서만 접근이 가능하다.
	즉, 멤버메소드만 제대로 정의된다면 멤버변수의 값을 마음대로
	변경하는 행위가 문법적으로 허용되지 않는다.
	 */
	private String name;
	private String phoneNumber;
	private String birthday;
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
	//getter()/setter()정의
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
}






