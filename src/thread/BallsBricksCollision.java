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
			for(int i = 0; i < this.balls.size(); i++) {
				for(int j = 0; j < this.bricks.size(); j++) {
					synchronized (this.balls.get(i)) {
						this.balls.get(i).collide(this.bricks.get(j));
					}
					synchronized (this.bricks) {
						if(this.bricks.get(j).intersect(this.balls.get(i))) {
							this.bricks.remove(this.bricks.get(j));
						}
					}
				}
			}
		}
	}
	
}
