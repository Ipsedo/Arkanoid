package thread;

import java.util.List;

import object.Ball;
import object.Brick;
import object.Score;

public class BallsBricksCollision extends CancelableThread {

    private List<Brick> bricks;
    private List<Ball> balls;
    private Score score;

    /**
     * 
     * @param balls
     * @param bricks
     * @param score
     */
    public BallsBricksCollision(List<Ball> balls, List<Brick> bricks, Score score) {
	super("BallsBricksCollision");
	this.balls = balls;
	this.bricks = bricks;
	this.score = score;
    }

    /**
     * 
     */
    public void run() {
	// TODO régler le pb d'interblocage pas encore arrivé
	while (!this.canceled) {
	    synchronized (this.bricks) {
		for (int i = this.bricks.size() - 1; i >= 0; i--) {
		    Brick br = this.bricks.get(i);
		    synchronized (this.balls) {
			for (int j = this.balls.size() - 1; j >= 0; j--) {
			    Ball ba = this.balls.get(j);
			    br.collide(ba);
			    if (br.intersect(ba)) {
				synchronized (this.score) {
				    this.score.incrScore(br.getScore());
				}
			    }
			    if (!br.isAlive()) {
				this.bricks.remove(br);
			    }
			}
		    }
		}
	    }
	    try {
		Thread.sleep((long) CancelableThread.TIME_TO_WAIT);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
    }

}