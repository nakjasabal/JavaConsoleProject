package project1b.ver06;

public class AutoSaver extends Thread {

	AccountManager am ;
	
	public AutoSaver(AccountManager am) {
		this.am = am;
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				am.saveInfoTxt();
				sleep(1000);
			}
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
