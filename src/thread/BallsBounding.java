package thread;

import java.util.List;

import object.Ball;

public class BallsBounding extends CancelableThread {

    private List<Ball> balls;

    /**
     * BallsBounding Thread
     * 
     * @param balls
     *            La liste de balles que l'on veut faire rebondir ou detruire
     */
    public BallsBounding(List<Ball> balls) {
	super("BallsBounding");
	this.balls = balls;
    }

    /**
     * On fait rebondir les balles sur le terrain et si elles sont sorties, on
     * les enleve de la liste
     */
    public void run() {
	while (!this.canceled) {
	    synchronized (this.balls) {
		for (int i = this.balls.size() - 1; i >= 0; i--) {
		    if (this.balls.get(i).bounding()) {
			this.balls.remove(this.balls.get(i));
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
