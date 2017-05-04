package thread;

import java.util.ArrayList;

import object.Ball;
import object.Paddle;

public class PaddleBounding extends Thread {

	private boolean canceled;
	private Paddle paddle;
	private ArrayList<Ball> balls;
	
	public PaddleBounding(ArrayList<Ball> balls, Paddle paddle) {
		this.balls = balls;
		this.paddle = paddle;
		this.canceled = false;
	}
	
	public void setCancel(boolean canceled){
		this.canceled = canceled;
	}
	
	public void run() {
		while(!this.canceled) {
			for(Ball b : this.balls) {
				synchronized (b) {
					this.paddle.ballBouding(b);
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
