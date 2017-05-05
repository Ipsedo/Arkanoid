package thread;

import java.util.ArrayList;

import object.Ball;
import object.Paddle;

public class PaddleBounding extends CancelableThread {

	private Paddle paddle;
	private ArrayList<Ball> balls;
	
	public PaddleBounding(ArrayList<Ball> balls, Paddle paddle) {
		super();
		this.balls = balls;
		this.paddle = paddle;
	}
	
	public void run() {
		while(!this.canceled) {
			for(Ball b : this.balls) {
				synchronized (b) {
					b.collide(this.paddle);
				}
			}
			try {
				Thread.sleep(10L);
			} catch(InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}
}
