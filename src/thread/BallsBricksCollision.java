package thread;

import java.util.ArrayList;

import object.Ball;
import object.Brick;

public class BallsBricksCollision extends CancelableThread {

	private ArrayList<Brick> bricks;
	private ArrayList<Ball> balls;
	
	public BallsBricksCollision(ArrayList<Ball> balls, ArrayList<Brick> bricks) {
		this.balls = balls;
		this.bricks = bricks;
	}
	
	public void run() {
		while(!this.canceled) {
			for(Ball ba : this.balls) {
				for(int i = 0; i < this.bricks.size(); i++) {
					synchronized (ba) {
						ba.collide(this.bricks.get(i));
					}
					synchronized (this.bricks) {
						if(this.bricks.get(i).intersect(ba)) {
							this.bricks.remove(this.bricks.get(i));
						}
					}
				}
			}
		}
	}
	
}
