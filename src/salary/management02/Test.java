package salary.management02;

class PhoneInfo{
	String name;
	public void showInfo() {
		System.out.println("이름:"+name);
	}
}
class CompanyInfo extends PhoneInfo{
	@Override
	public void showInfo()
	{		
		super.showInfo();
		System.out.println("회사확장:출력");
	}
}
class SchoolInfo extends PhoneInfo {
	@Override
	public void showInfo()
	{
		super.showInfo();
		System.out.println("학교확장:출력");
	}
}

public class Test
{
	public static void main(String[] args)
	{
       
	}
}
