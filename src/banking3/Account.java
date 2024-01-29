package banking3;

public abstract class Account {

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
		//System.out.println("----------------------");
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

		if(accMoney < money) {
			//잔고보다 출금액이 클 경우...
			System.out.println("잔고부족. 금액전체를"
					+ " 출금할까요?(y or n)");
			String qu = AccountManager.sc.nextLine();
			if(qu.equalsIgnoreCase("Y")) {
				//금액 전체 출금인 경우 잔고를 0으로
				//처리하면 된다.
				accMoney = 0;
			}
			else {
				System.out.println("출금요청이 취소됨");
				return false;
			}
		}
		else {
			//잔고보다 출금액이 작거나 같은경우...
			accMoney -= money;
		}

		return true;
	}

}









