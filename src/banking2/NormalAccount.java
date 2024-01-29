package banking2;

public class NormalAccount extends Account {

	//멤버변수
	int interRate; //기본이자율

	//생성자
	public NormalAccount(String id, String name,
			int money, int rate) {
		//부모클래스의 생성자 호출하기
		super(id, name, money);
		//내 멤버변수 초기화
		this.interRate = rate;
	}

	//메소드 오버라이드 처리
	@Override
	public void showAccountInfo() {
		//부모클래스의 멤버메소드 호출하기
		super.showAccountInfo();

		System.out.println("기본이자:"+ interRate +"%");
		System.out.println("-----------------------");
	}

	//메소드 오버라이드 : (원금 * 이자율) + 입금액
	@Override
	public boolean plusAccMoney(int money)
	{
		System.out.println("NormalAccount>plusAccMoney메소드");

		//원금을 가져온다.
		int pMoney = getAccMoney();

		//이자계산 방식에 따라 계산한다.
		double calMoney = pMoney + (pMoney * ((double)interRate/100)) + money;
		//System.out.println("원금>"+pMoney+" , 이자>"+interRate+", 입금>"+money);
		//System.out.println("이자계산 방식에 따라 계산한다>"+calMoney);

		//계산한 금액을 원금에 대입한다.
		setAccMoney((int)calMoney);

		return true;
	}
}




