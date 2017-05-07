package thread;

import java.util.List;

import object.Ball;
import object.Brick;

public class BallsBricksCollision extends CancelableThread {

    private List<Brick> bricks;
    private List<Ball> balls;

    /**
     * 
     * @param balls
     * @param bricks
     */
    public BallsBricksCollision(List<Ball> balls, List<Brick> bricks) {
	super("BallsBricksCollision");
	this.balls = balls;
	this.bricks = bricks;
    }

    /**
     * Big pb pas encore arrivé d'interblocage : on demande d'abord la liste
     * puis un de ses élément
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
				this.bricks.remove(br);

			    }

			}
		    }
		}
	    }
	    try {
		Thread.sleep(10L);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
    }

}