package thread;

import java.util.ArrayList;

import object.Ball;

public class BallsMove extends Thread {
	
	private boolean canceled;
	private ArrayList<Ball> balls;
	
	public BallsMove(ArrayList<Ball> balls) {
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
					b.move(new float[]{0f, 0f});
				}
			}
			try {
				Thread.sleep(50L);
			} catch(InterruptedException ie) {
				ie.printStackTrace();
			}
		}
		
	}

}
