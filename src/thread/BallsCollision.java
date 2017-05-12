package thread;

import java.util.List;

import object.Ball;

public class BallsCollision extends CancelableThread {

    private List<Ball> balls;

    /**
     * BallsCollision : Thread permettant les collision inter-balles
     * 
     * @param balls
     *            La liste de balles
     */
    public BallsCollision(List<Ball> balls) {
	super("BallsCollision");
	this.balls = balls;
    }

    /**
     * On check les collisions de balles et on les fait rebondir si il y a
     * collision
     */
    public void run() {
	while (!this.canceled) {
	    synchronized (this.balls) {
		for (int i = this.balls.size() - 1; i >= 0; i--) {
		    for (int j = i - 1; j >= 0; j--) {
			this.balls.get(i).collide(this.balls.get(j));
			this.balls.get(j).collide(this.balls.get(i));
		    }
		}
	    }
	    try {
		Thread.sleep((long) CancelableThread.TIME_TO_WAIT);
	    } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
    }
}
