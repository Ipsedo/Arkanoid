package thread;

import java.util.List;

import object.Ball;

public class BallsCollision extends CancelableThread {

    private List<Ball> balls;

    /**
     * 
     * @param balls
     */
    public BallsCollision(List<Ball> balls) {
	super("BallsCollision");
	this.balls = balls;
    }

    /**
     * 
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
		Thread.sleep(CancelableThread.TIME_TO_WAIT);
	    } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
    }
}
