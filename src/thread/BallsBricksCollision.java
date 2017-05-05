package thread;

import java.util.ArrayList;

import object.Ball;
import object.Brick;

public class BallsBricksCollision extends CancelableThread {

	private ArrayList<Brick> bricks;
	private ArrayList<Ball> balls;
	
	public BallsBricksCollision(ArrayList<Ball> balls, ArrayList<Brick> bricks) {
		super("BallsBricksCollision");
		this.balls = balls;
		this.bricks = bricks;
	}
	
	public void run() {
		
		while(!this.canceled) {
			for(int i = this.bricks.size() - 1; i >= 0; i--) {
				Brick br = this.bricks.get(i);
				synchronized (br) {
					for(int j = 0; j < this.balls.size(); j++) {
						Ball ba = this.balls.get(j);
						synchronized (ba) {
							ba.collide(br);
							if(br.intersect(ba)) {
								synchronized(this.bricks) {
									this.bricks.remove(br);
								}
							}
						}
					}
				}
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}