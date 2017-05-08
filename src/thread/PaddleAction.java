package thread;

import game.MyJFrame;

import java.util.List;

import object.Ball;
import object.Brick;
import object.Paddle;

public class PaddleAction extends CancelableThread {

    private Paddle paddle;
    private List<Ball> balls;

    /**
     * 
     * @param balls
     * @param paddle
     */
    public PaddleAction(List<Ball> balls, Paddle paddle) {
	super("PaddleAction");
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
		    if (this.paddle.intersect(this.balls.get(i))) {
			synchronized (CancelableThread.class) {
			    CancelableThread.TIME_TO_WAIT -= 0.02f;
			    if (CancelableThread.TIME_TO_WAIT < 0) {
				CancelableThread.TIME_TO_WAIT = 0f;
			    }
			}
		    }
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
