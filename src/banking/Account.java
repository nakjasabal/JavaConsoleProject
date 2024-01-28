package banking;

import java.io.Serializable;

public abstract class Account implements Serializable {

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
		System.out.println("---------계좌정보-------");
		System.out.print("계좌번호:"+accountID);
		System.out.print(", 고객이름:"+customName);
		System.out.print(", 잔고:"+accMoney);
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

	


/*
	Map컬렉션에 동일한 인스턴스가 입력되는지 확인하기
	위해 Object클래스의 equals()메소드와 hashCode()
	메소드를 오버라이딩 처리한다.
	여기서는 계좌번호가 동일할때 동일한 인스턴스로
	간주한다.
	 */
	@Override
	public boolean equals(Object obj) {

		//Object타입으로 전달된 매개변수를 Account타입
		//으로 형변환하기(즉, 다운케스팅)
		Account cmpObj = (Account)obj;
		if(accountID.compareTo(cmpObj.accountID)==0) {
			/*
			매개변수로 전달된 객체의 계좌번호와 현재
			객체의 계좌번호가 동일하다면 true를 반환함.
			*/
			System.out.println("equals():true");
			return true;
		}
		else {
			/*
			두 객체의 계좌번호가 동일하지 않다면 false
			반환함.
			*/
			System.out.println("equals():false");
			return false;
		}
	}
	@Override
	public int hashCode() {
		/*
		동일한 객체인지를 확인하기 위해 equals()메소드를
		오버라이딩 처리할때 반드시 hahsCode()메소드도
		같이 오버라이딩 처리해야 두 객체간 비교를 할 수
		있다.
		 */
		System.out.println("hashCode():"+accountID.hashCode());
		return accountID.hashCode();
	}
	
	@Override
	public String toString() {		
		return String.format("계좌번호:%s, 고객이름:%s, 잔고:%s", accountID, customName, accMoney);
	}
}














