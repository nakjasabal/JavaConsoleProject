package banking7.jdbc;

public class Account {

	//멤버변수(계좌번호(String형), 이름(String형), 잔액(int형))
	private String accountID;//계좌번호
	private String customName;//고객이름
	private int accMoney;//잔고
	
	//생성자
	public Account(String id, String name, int money) {
		this.accountID = id;
		this.customName = name;
		this.accMoney = money;	
	}

	//멤버메소드 : 계좌정보출력
	public void showAccountInfo() {
		System.out.println("-------계좌정보-------");
		System.out.println("계좌번호:"+accountID);
		System.out.println("고객이름:"+customName);
		System.out.println("잔고:"+accMoney);
		System.out.println("----------------------");
	}
	//getter/setter 정의
	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	public String getCustomName() {
		return customName;
	}

	public void setCustomName(String customName) {
		this.customName = customName;
	}

	public int getAccMoney() {
		return accMoney;
	}

	public void setAccMoney(int accMoney) {
		this.accMoney = accMoney;
	}

	//입금처리
	public boolean plusAccMoney(int money) {
		//ver01에서는 별도의 검사를 하지않고 입금처리함
		accMoney += money;
		return true;
	}
	//출금처리
	public boolean minusAccMoney(int money) {
		//ver01에서는 잔고가 마이너스 처리되더라도
		//조건검사없이 출금처리함
		accMoney -= money;
		return true;
	}

}









