package thread;

public class CancelableThread extends Thread {

	protected boolean canceled;
	
	public CancelableThread(String name){
		super(name);
		this.canceled = false;
	}
	
	public void setCancel(boolean canceled){
		this.canceled = canceled;
	}
	
}
