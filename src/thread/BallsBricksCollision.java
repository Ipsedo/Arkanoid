package thread;

import game.MyJPanel;

import java.util.List;
import java.util.Random;

import object.Ball;
import object.Brick;
import object.Paddle;
import object.Particule;
import object.Score;
import util.Sound;

public class BallsBricksCollision extends CancelableThread {

    private List<Brick> bricks;
    private List<Ball> balls;
    private Score score;
    private MyJPanel jpanel;
    private Random rand;
    private Paddle paddle;
    private List<Particule> points;

    /**
     * 
     * @param balls
     * @param bricks
     * @param score
     * @param jpanel
     * @param paddle
     */
    public BallsBricksCollision(List<Ball> balls, List<Brick> bricks, Score score, MyJPanel jpanel,
	    Paddle paddle, List<Particule> points) {
	super("BallsBricksCollision");
	this.balls = balls;
	this.bricks = bricks;
	this.score = score;
	this.jpanel = jpanel;
	this.rand = new Random(System.currentTimeMillis());
	this.paddle = paddle;
	this.points = points;
    }

    /**
     * 
     */
    public void run() {
	Random rand = new Random(System.currentTimeMillis());
	while (!this.canceled) {
	    synchronized (this.bricks) {
		for (int i = this.bricks.size() - 1; i >= 0; i--) {
		    Brick br = this.bricks.get(i);
		    synchronized (this.balls) {
			for (int j = this.balls.size() - 1; j >= 0; j--) {
			    Ball ba = this.balls.get(j);
			    br.collide(ba);
			    if (br.intersect(ba)) {
				Sound.brickSound();
				this.score.incrScore(br.getScore());
				synchronized (this.points) {
				    br.makeExplosion(this.points, this.jpanel);
				}
				br.makeBonus(this.paddle, this.balls, this.jpanel, rand);
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