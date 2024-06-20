package bankingT2;

public class Account {
	// 계좌번호(String형), 이름(String형), 잔액(int형) 
	public String accNumber;
	public String name;
	public int balance;
	//생성자
	public Account() {}
	public Account(String accNumber, String name, int balance) {
		this.accNumber = accNumber;
		this.name = name;
		this.balance = balance;
	}
	//정보출력용 메서드 
	public void showAccount() {
		System.out.println("계좌번호:"+ this.accNumber);
		System.out.println("고객이름:"+ this.name);
		System.out.println("잔고:"+ this.balance);
	}
	//입금처리
	public void deposit(int money) {
		this.balance += money;
	}
	
	/*
	아래 2개의 메서드를 오버라이딩하면 Set에 인스턴스를 추가할때 자동으로 
	아래 메서드를 호출하여 중복된 인스턴스인지 확인한다. 
	여기서는 계좌번호에 대한 부분만 정의한다. 
	 */
	@Override
	public int hashCode() {
		//계좌번호의 해시값을 반환
		return accNumber.hashCode();
	}	
	@Override
	public boolean equals(Object obj) {		
		Account acc = (Account) obj;
		//계좌번호가 동일한 값인지 확인하여 boolean값 반환 
		if(acc.accNumber.equals(this.accNumber)) {
			return true;
		}
		else {
			return false;
		}
	}
}
