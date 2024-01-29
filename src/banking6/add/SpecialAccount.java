package banking6.add;

public class SpecialAccount extends NormalAccount {
	
	int depositCount;

	public SpecialAccount(String id, String name, int money, int rate, int depositCount) {
		super(id, name, money, rate);
		this.depositCount = depositCount;
	}
	
	
	@Override
	public void showAccountInfo() {
		super.showAccountInfo();
		System.out.println("입금횟수:"+ this.depositCount +"회");
		//System.out.println("-----------------------");	
	}
	
	@Override
	public boolean plusAccMoney(int money) {
		//원금을 가져온다.
		int pMoney = getAccMoney();
		this.depositCount++;

		double calMoney = pMoney + (pMoney * ((double)interRate/100)) + money;
		if(this.depositCount!=0 && this.depositCount%2==0) {
			System.out.println("축하금이 지급되었습니다^^*");
			calMoney += 500;
		}

		setAccMoney((int)calMoney);		
		return true;
	}

	@Override
	public String toString() {
		String p1 = "계좌번호=" + getAccountID() + ", 이름=" + getCustomName() 
			+ ", 잔고=" + getAccMoney();
		String p2 = ", 기본이자=" + super.interRate;
		return p1 + p2 + ", 입금횟수="+ this.depositCount;
	}
}
