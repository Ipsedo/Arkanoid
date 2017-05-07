package thread;

import java.util.List;

import object.Ball;

public class BallsMove extends CancelableThread {

    private List<Ball> balls;

    /**
     * 
     * @param balls
     */
    public BallsMove(List<Ball> balls) {
	super("BallsMove");
	this.balls = balls;
    }

    /**
     * 
     */
    public void run() {
	while (!this.canceled) {
	    synchronized (this.balls) {
		for (int i = this.balls.size() - 1; i >= 0; i--) {
		    this.balls.get(i).move(new float[2]);
		}
	    }
	    try {
		Thread.sleep(CancelableThread.TIME_TO_WAIT);
	    } catch (InterruptedException ie) {
		ie.printStackTrace();
	    }
	}

    }

}
