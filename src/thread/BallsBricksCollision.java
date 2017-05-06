package thread;

import java.util.ArrayList;

import object.Ball;
import object.Brick;

public class BallsBricksCollision extends CancelableThread {

    private ArrayList<Brick> bricks;
    private ArrayList<Ball> balls;

    /**
     * 
     * @param balls
     * @param bricks
     */
    public BallsBricksCollision(ArrayList<Ball> balls, ArrayList<Brick> bricks) {
	super("BallsBricksCollision");
	this.balls = balls;
	this.bricks = bricks;
    }

    /**
     * Big pb pas encore arrivé d'interblocage : on demande d'abord la liste puis un de ses élément
     */
    public void run() {
	//TODO régler le pb d'interblocage pas encore arrivé
	while (!this.canceled) {
	    for (int i = this.bricks.size() - 1; i >= 0; i--) {
		Brick br = this.bricks.get(i);
		synchronized (br) {
		    for (int j = 0; j < this.balls.size(); j++) {
			Ball ba = this.balls.get(j);
			synchronized (ba) {
			    br.collide(ba);
			    if (br.intersect(ba)) {
				synchronized (this.bricks) {
				    this.bricks.remove(br);
				}
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