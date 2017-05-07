package thread;

import java.util.List;

import object.Ball;
import object.Paddle;

public class PaddleBounding extends CancelableThread {

    private Paddle paddle;
    private List<Ball> balls;

    /**
     * 
     * @param balls
     * @param paddle
     */
    public PaddleBounding(List<Ball> balls, Paddle paddle) {
	super("PaddleBounding");
	this.balls = balls;
	this.paddle = paddle;
    }

    /**
     * 
     */
    public void run() {
	while (!this.canceled) {
	    synchronized (this.balls) {
		for (int i = this.balls.size() - 1; i >= 0; i--) {
		    this.paddle.collide(this.balls.get(i));
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
