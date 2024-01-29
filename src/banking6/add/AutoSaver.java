package banking6.add;

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
				sleep(3000);
			}
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
