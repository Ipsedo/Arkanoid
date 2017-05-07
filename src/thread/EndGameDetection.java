package thread;

import game.MyJFrame;
import game.MyJPanel;

import java.util.ArrayList;
import java.util.List;

import object.Ball;
import object.Brick;

public class EndGameDetection extends CancelableThread {

    private List<Ball> balls;
    private List<Brick> bricks;
    private MyJPanel jpanel;
    private MyJFrame jframe;

    /**
     * 
     * @param balls
     * @param bricks
     * @param jpanel
     */
    public EndGameDetection(List<Ball> balls, List<Brick> bricks, MyJPanel jpanel, MyJFrame jframe) {
	super("EndGameDetection");
	this.bricks = bricks;
	this.balls = balls;
	this.jpanel = jpanel;
	this.jframe = jframe;
    }

    /**
     * 
     */
    public void run() {
	while (!this.canceled) {
	    if (this.balls.isEmpty()) {
		synchronized (this.jpanel) {
		    this.jpanel.setDead(true);
		}
	    } else if (this.bricks.isEmpty()) {
		synchronized (this.jpanel) {
		    this.jpanel.setWinner(true);
		}
	    }
	    try {
		Thread.sleep(10L);
	    } catch (InterruptedException ie) {
		ie.printStackTrace();
	    }
	}
    }
}
