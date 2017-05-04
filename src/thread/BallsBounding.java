package thread;

import java.util.ArrayList;

import object.Ball;

public class BallsBounding extends Thread {

	private boolean canceled;
	private ArrayList<Ball> balls;
	
	public BallsBounding(ArrayList<Ball> balls) {
		this.balls = balls;
		this.canceled = false;
	}
	
	public void setCancel(boolean canceled){
		this.canceled = canceled;
	}
	
	public void run() {
		while(!this.canceled) {
			for(Ball b : this.balls) {
				synchronized (b) {
					b.bounding();
				}
			}
			try {
				Thread.sleep(1000L / 120L);
			} catch(InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}
}
