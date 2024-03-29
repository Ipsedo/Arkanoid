package thread;

import java.util.List;

import object.Ball;
import object.Paddle;
import util.Sound;

public class PaddleAction extends CancelableThread {

    private Paddle paddle;
    private List<Ball> balls;

    /**
     * PaddleAction : Thread permettant le rebond balles - raquette ainsi que
     * l'acceleration legere du jeu
     * 
     * @param balls
     *            La liste de balles
     * @param paddle
     *            La raquette
     */
    public PaddleAction(List<Ball> balls, Paddle paddle) {
	super("PaddleAction");
	this.balls = balls;
	this.paddle = paddle;
    }

    /**
     * On fait rebondir les balles avec la raquette et si il y a intersection on
     * accelere le jeu
     */
    public void run() {
	while (!this.canceled) {
	    synchronized (this.balls) {
		for (int i = this.balls.size() - 1; i >= 0; i--) {
		    this.paddle.collide(this.balls.get(i));
		    if (this.paddle.intersect(this.balls.get(i))) {
			Sound.paddleSound();
			synchronized (CancelableThread.class) {
			    CancelableThread.TIME_TO_WAIT -= 0.005f;
			    if (CancelableThread.TIME_TO_WAIT < 0) {
				CancelableThread.TIME_TO_WAIT = 0f;
			    }
			}
		    }
		}
	    }
	    try {
		Thread.sleep((long) CancelableThread.TIME_TO_WAIT);
	    } catch (InterruptedException ie) {
		ie.printStackTrace();
	    }
	}
    }
}
