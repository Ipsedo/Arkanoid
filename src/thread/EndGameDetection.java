package thread;

import java.util.List;

import game.GameInfoJPanel;
import game.MyJFrame;
import game.MyJPanel;
import object.Ball;
import object.Brick;

public class EndGameDetection extends CancelableThread {

    private List<Ball> balls;
    private List<Brick> bricks;
    private MyJPanel jpanel;
    private MyJFrame jframe;
    private GameInfoJPanel gameInfo;

    /**
     * 
     * @param balls
     * @param bricks
     * @param jpanel
     * @param jframe
     */
    public EndGameDetection(List<Ball> balls, List<Brick> bricks, MyJPanel jpanel, MyJFrame jframe,
	    GameInfoJPanel gameInfo) {
	super("EndGameDetection");
	this.bricks = bricks;
	this.balls = balls;
	this.jpanel = jpanel;
	this.jframe = jframe;
	this.gameInfo = gameInfo;
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
		this.jframe.pauseGame();

	    } else if (this.bricks.isEmpty()) {
		synchronized (this.jpanel) {
		    this.jpanel.setWinner(true);
		}

		Thread tmp =  new Thread() {
		    public void run() {
			EndGameDetection.this.jframe.pauseGame();
			try {
			    Thread.sleep(10L);
			} catch (InterruptedException e) {
			    // TODO Auto-generated catch block
			    e.printStackTrace();
			}
			EndGameDetection.this.gameInfo.levelDone();
		    }
		};
		try {
		    Thread.sleep(1000L);
		} catch (InterruptedException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		tmp.start();
	    }
	    try {
		Thread.sleep((long) CancelableThread.TIME_TO_WAIT);
	    } catch (InterruptedException ie) {
		ie.printStackTrace();
	    }
	}
    }
}
