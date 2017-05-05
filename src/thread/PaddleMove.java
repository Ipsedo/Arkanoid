package thread;

import object.Ball;
import object.Paddle;

public class PaddleMove extends CancelableThread {

	private Paddle paddle;
	
	public PaddleMove(Paddle paddle) {
		super();
		this.paddle = paddle;
	}
	
	public void run() {
		while(!this.canceled) {
			synchronized(this.paddle) {
				this.paddle.move(new float[2]);
			}
			try {
				Thread.sleep(10L);
			} catch(InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}
}
