package thread;

import object.Ball;
import object.Paddle;

public class PaddleMove extends CancelableThread {

    private Paddle paddle;

    /**
     * PaddleMove : Thread permettant les mouvements de la raquette
     * 
     * @param paddle
     *            La raquette
     */
    public PaddleMove(Paddle paddle) {
	super("PaddleMove");
	this.paddle = paddle;
    }

    /**
     * On fait bouger la raquette avec une acceleration nulle
     */
    public void run() {
	while (!this.canceled) {
	    synchronized (this.paddle) {
		this.paddle.move(new float[2]);
	    }
	    try {
		Thread.sleep((long) CancelableThread.TIME_TO_WAIT);
	    } catch (InterruptedException ie) {
		ie.printStackTrace();
	    }
	}
    }
}
