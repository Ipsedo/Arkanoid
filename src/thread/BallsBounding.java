package thread;

import java.util.List;

import object.Ball;

public class BallsBounding extends CancelableThread {

    private List<Ball> balls;

    /**
     * 
     * @param balls
     */
    public BallsBounding(List<Ball> balls) {
	super("BallsBounding");
	this.balls = balls;
    }

    /**
     * 
     */
    public void run() {
	while (!this.canceled) {
	    synchronized (this.balls) {
		for (int i = this.balls.size() - 1; i >= 0; i--) {
		    if (this.balls.get(i).bounding()) {
			this.balls.remove(this.balls.get(i));
		    }
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
