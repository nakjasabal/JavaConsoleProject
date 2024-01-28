package project1a.ver08;

public class AutoSaver extends Thread {

	PhoneBookManager pm ;
	
	public AutoSaver(PhoneBookManager pm) {
		this.pm = pm;
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				pm.saveInfoTxt();
				sleep(1000);
			}
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
