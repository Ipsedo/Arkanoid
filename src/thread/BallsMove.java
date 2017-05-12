package thread;

import java.util.List;

import object.Ball;

public class BallsMove extends CancelableThread {

    private List<Ball> balls;

    /**
     * BallsMove : Thread permettant le deplacement des balles
     * 
     * @param balls
     *            La liste de balles
     */
    public BallsMove(List<Ball> balls) {
	super("BallsMove");
	this.balls = balls;
    }

    /**
     * On fait bouger les balles une a une
     */
    public void run() {
	while (!this.canceled) {
	    synchronized (this.balls) {
		for (int i = this.balls.size() - 1; i >= 0; i--) {
		    this.balls.get(i).move(new float[2]);
		}
	    }
	    try {
		Thread.sleep((long) CancelableThread.TIME_TO_WAIT);
	    } catch (InterruptedException ie) {
		ie.printStackTrace();
	    }
	}

    }

}
