package bankingT3;

import java.util.HashSet;

public class AutoSaver extends Thread {

	/*
	쓰레드는 메니져클래스에 저장된 컬렉션에 접근하여 데이터를 파일로 저장해야한다. 
	따라서 이 클래스에서 새로운 인스턴스를 생성하면 안되고, Main클래스에서 생성한
	인스턴스를 받아서 사용해야한다. 
	 */
	AccountManager am ;	
	public AutoSaver(AccountManager am) {
		this.am = am;		
	}
	
	@Override
	public void run() {
		try {
			/*
			이 쓰레드는 데몬쓰레드로 만들 예정이므로 무한루프로 구성한다. 
			프로그램이 종료되면 같이 종료된다. 
			 */
			while(true) {
				/*
				3초에 한번씩 메니져 클래스의 메서드를 호출하여 자동저장 기능을
				수행한다. 
				 */
				am.saveInfoTxt();
				sleep(3000);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}


