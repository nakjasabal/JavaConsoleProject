package project1b.ver04;

import project1b.ver02.CustomSpecialRate;

public class HighCreditAccount extends Account{

	//멤버변수
	int interRate;//기본이자
	String customGrade;//고객의 신용등급

	//생성자
	public HighCreditAccount(String id, String name,
			int money, int iRate, String grade) {
		super(id, name, money);
		this.interRate = iRate;
		this.customGrade = grade;
	}

	//메소드 오버라이드
	@Override
	public void showAccountInfo() {
		super.showAccountInfo();
		System.out.println("이자율:"+ interRate +"%");
		System.out.println("신용등급:"+ customGrade);
		System.out.println("------------------------");
	}

	//메소드 오버라이드 : (원금 * 이자율 * 특별이자) + 입금액
	@Override
	public boolean plusAccMoney(int money)
	{
		System.out.println("HighCreditAccount>plusAccMoney메소드");

		//원금을 가져온다.
		int pMoney = getAccMoney();
		double calMoney=0;

		//이자계산 방식에 따라 계산한다.
		/*
		 * A,  B,  C 등급별
		 * 7%, 4%, 2% 이자 추가지급
		 * */
		if(customGrade.equalsIgnoreCase("A")){
			calMoney = pMoney + (pMoney * (interRate/100.0)) + 
						(pMoney * (CustomSpecialRate.A/100.0)) + money;
		}
		else if(customGrade.equalsIgnoreCase("B")){
			calMoney = pMoney + (pMoney * (interRate/100.0)) + 
						(pMoney * (CustomSpecialRate.B/100.0)) + money;
		}
		else if(customGrade.equalsIgnoreCase("C")){
			calMoney = pMoney + (pMoney * (interRate/100.0)) + 
						(pMoney * (CustomSpecialRate.C/100.0)) + money;
		}

		//계산한 금액을 원금에 대입한다.
		setAccMoney((int)calMoney);

		return true;
	}

}







