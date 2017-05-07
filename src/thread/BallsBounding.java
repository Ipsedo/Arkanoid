package thread;

import java.util.ArrayList;

import object.Ball;

public class BallsBounding extends CancelableThread {

    private ArrayList<Ball> balls;

    /**
     * 
     * @param balls
     */
    public BallsBounding(ArrayList<Ball> balls) {
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
		    synchronized (this.balls.get(i)) {
			if (this.balls.get(i).bounding()) {
			    this.balls.remove(this.balls.get(i));

			}
		    }
		}
	    }
	    try {
		Thread.sleep(10L);
	    } catch (InterruptedException ie) {
		ie.printStackTrace();
	    }
	}
    }
}
