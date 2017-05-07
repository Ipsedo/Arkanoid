package thread;

public class CancelableThread extends Thread {
    
    public static long TIME_TO_WAIT = 10L;

    protected boolean canceled;

    /**
     * 
     * @param name
     */
    public CancelableThread(String name) {
	super(name);
	this.canceled = false;
    }

    /**
     * 
     * @param canceled
     */
    public void setCancel(boolean canceled) {
	this.canceled = canceled;
    }

}
