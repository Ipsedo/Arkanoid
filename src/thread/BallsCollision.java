package thread;

import java.util.ArrayList;

import object.Ball;

public class BallsCollision extends CancelableThread {

    private ArrayList<Ball> balls;

    /**
     * 
     * @param balls
     */
    public BallsCollision(ArrayList<Ball> balls) {
	super("BallsCollision");
	this.balls = balls;
    }

    /**
     * 
     */
    public void run() {
	while (!this.canceled) {
	    for (int i = this.balls.size() - 1; i >= 0; i--) {
		for (int j = i - 1; j >= 0; j--) {
		    synchronized (this.balls.get(j)) {
			this.balls.get(i).collide(this.balls.get(j));
		    }
		    synchronized (this.balls.get(i)) {
			this.balls.get(j).collide(this.balls.get(i));
		    }
		}

	    }
	    try {
		Thread.sleep(10L);
	    } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
    }
}
