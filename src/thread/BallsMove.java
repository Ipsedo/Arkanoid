package thread;

import java.util.ArrayList;

import object.Ball;

public class BallsMove extends CancelableThread {
	
	private ArrayList<Ball> balls;
	
	public BallsMove(ArrayList<Ball> balls) {
		super();
		this.balls = balls;
	}
	
	public void run() {
		while(!this.canceled) {
			for(Ball b : this.balls) {
				synchronized (b) {
					b.move(new float[]{0f, 0f});
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
