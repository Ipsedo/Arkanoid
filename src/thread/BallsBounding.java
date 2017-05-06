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
	    for (int i = 0; i < this.balls.size(); i++) {
		synchronized (this.balls.get(i)) {
		    this.balls.get(i).bounding();
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
