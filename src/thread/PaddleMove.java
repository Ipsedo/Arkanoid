package thread;

import object.Ball;
import object.Paddle;

public class PaddleMove extends Thread {

	private boolean canceled;
	private Paddle paddle;
	
	public PaddleMove(Paddle paddle) {
		this.paddle = paddle;
		this.canceled = false;
	}
	
	public void setCancel(boolean canceled){
		this.canceled = canceled;
	}
	
	public void run() {
		while(!this.canceled) {
			this.paddle.move(new float[2]);
			try {
				Thread.sleep(50L);
			} catch(InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}
}
