package project1a.ver08;


public class PhoneSchoolInfo extends PhoneInfo {

	//확장된 멤버변수
	private String major;//전공
	private int year;//학년

	//생성자
	public PhoneSchoolInfo(String n, String p, String m, int y) {
		super(n, p);//부모생성자 호출
		major = m;
		year = y;
	}
	@Override
	public void showPhoneInfo() {
		//super.showPhoneInfo();//부모클래스의 멤버메소드 호출
		System.out.print("이름:"+ getName());
		System.out.print(", 전화번호:"+ getPhoneNumber());
		System.out.print(", 전공과목:"+ major);
		System.out.print(", 학년:"+ year +"\n");
	}
	@Override
	public String toString() {		
		return String.format("이름:%s, 전화번호:%s, 전공과목:%s, 학년:%d%n", 
				getName(), getPhoneNumber(), major, year);
	}
	 
}










