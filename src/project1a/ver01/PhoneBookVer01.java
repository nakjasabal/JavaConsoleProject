package project1a.ver01;

public class PhoneBookVer01 {

	public static void main(String[] args) {

		PhoneInfo p1 =
			new PhoneInfo("토니 스타크", "010-1111-2222",
					"70-01-01");
		PhoneInfo p2 =
			new PhoneInfo("브루스 베너", "010-2222-3333");

		p1.showPhoneInfo();
		p2.showPhoneInfo();

	}

}
