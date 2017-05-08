package thread;

import game.MyJFrame;

import java.util.List;

import object.Ball;
import object.Brick;

public class BallsBricksCollision extends CancelableThread {

    private List<Brick> bricks;
    private List<Ball> balls;
    private MyJFrame jframe;
    private long lastCollision;

    /**
     * 
     * @param balls
     * @param bricks
     */
    public BallsBricksCollision(List<Ball> balls, List<Brick> bricks, MyJFrame jframe) {
	super("BallsBricksCollision");
	this.balls = balls;
	this.bricks = bricks;
	this.jframe = jframe;
	this.lastCollision = System.currentTimeMillis();
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
			    if(br.intersect(ba)) {
				long deltaTime = System.currentTimeMillis() - this.lastCollision;
				deltaTime /= 3000L;
				this.jframe.incrScore((int) (1L / deltaTime));
			    }
			    if (!br.isAlive()) {
				this.bricks.remove(br);
			    }
			}
		    }
		}
	    }
	    try {
		Thread.sleep(CancelableThread.TIME_TO_WAIT);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
    }

}