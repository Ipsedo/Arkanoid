package thread;

import java.util.ArrayList;

import object.Ball;

public class BallsBounding extends CancelableThread {

	
	private ArrayList<Ball> balls;
	
	public BallsBounding(ArrayList<Ball> balls) {
		super();
		this.balls = balls;
	}
	
	public void run() {
		while(!this.canceled) {
			for(Ball b : this.balls) {
				synchronized (b) {
					b.bounding();
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
