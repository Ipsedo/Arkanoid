package thread;

import java.util.ArrayList;

import object.Ball;
import object.Paddle;

public class PaddleBounding extends CancelableThread {

    private Paddle paddle;
    private ArrayList<Ball> balls;

    /**
     * 
     * @param balls
     * @param paddle
     */
    public PaddleBounding(ArrayList<Ball> balls, Paddle paddle) {
	super("PaddleBounding");
	this.balls = balls;
	this.paddle = paddle;
    }

    /**
     * 
     */
    public void run() {
	while (!this.canceled) {
	    for (int i = this.balls.size() - 1; i >= 0; i--) {
		synchronized (this.balls.get(i)) {
		    this.paddle.collide(this.balls.get(i));
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
