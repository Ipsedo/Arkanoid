package thread;

import java.util.ArrayList;

import object.Ball;
import object.Paddle;

public class PaddleBounding extends CancelableThread {

	private Paddle paddle;
	private ArrayList<Ball> balls;
	
	public PaddleBounding(ArrayList<Ball> balls, Paddle paddle) {
		super("PaddleBounding");
		this.balls = balls;
		this.paddle = paddle;
	}
	
	public void run() {
		while(!this.canceled) {
			for(int i = 0; i < this.balls.size(); i++) {
				synchronized (this.balls.get(i)) {
					this.balls.get(i).collide(this.paddle);
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
