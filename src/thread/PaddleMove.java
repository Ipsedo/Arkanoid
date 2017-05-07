package thread;

import object.Ball;
import object.Paddle;

public class PaddleMove extends CancelableThread {

    private Paddle paddle;

    /**
     * 
     * @param paddle
     */
    public PaddleMove(Paddle paddle) {
	super("PaddleMove");
	this.paddle = paddle;
    }

    /**
     * 
     */
    public void run() {
	while (!this.canceled) {
	    synchronized (this.paddle) {
		this.paddle.move(new float[2]);
	    }
	    try {
		Thread.sleep(CancelableThread.TIME_TO_WAIT);
	    } catch (InterruptedException ie) {
		ie.printStackTrace();
	    }
	}
    }
}
