package thread;

public class CancelableThread extends Thread {

    public static float TIME_TO_WAIT = 5f;

    protected boolean canceled;

    /**
     * Initialise une thread ayant un attribut boolean pour la fin de thread
     * 
     * @param name
     *            Le nom de la Thread
     */
    public CancelableThread(String name) {
	super(name);
	this.canceled = false;
    }

    /**
     * Passer La condition de boucle de la thread
     * 
     * @param canceled
     *            Le boolean à passer
     */
    public void setCancel(boolean canceled) {
	this.canceled = canceled;
    }

}
