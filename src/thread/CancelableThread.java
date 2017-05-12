package thread;

public class CancelableThread extends Thread {

    public static float TIME_TO_WAIT = 5f;

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
     * Passer
     * @param canceled
     */
    public void setCancel(boolean canceled) {
	this.canceled = canceled;
    }

}
