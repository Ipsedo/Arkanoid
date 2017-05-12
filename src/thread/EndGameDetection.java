package thread;

import game.GameInfoJPanel;
import game.MyJFrame;
import game.MyJPanel;

import java.util.List;

import object.Ball;
import object.Brick;

public class EndGameDetection extends CancelableThread {

    private List<Ball> balls;
    private List<Brick> bricks;
    private MyJPanel jpanel;
    private MyJFrame jframe;
    private GameInfoJPanel gameInfo;

    /**
     * EndGameDetection : Thread permettant la detection de fin d'un niveau
     * 
     * @param balls
     *            La liste de balles
     * @param bricks
     *            La liste de briques
     * @param jpanel
     *            Le MyJPanel contenant le graphisme
     * @param jframe
     *            La MyJFrame contenant le jeu
     * @param gameInfo
     *            Le GameInfoJPanel contenant l'interface de jeu
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
     * On arrete la partie si il n'y a plus de briques ou de balles. Si la
     * partie est perdue, on met le jeu en pause en ayant preciser au jpanel de
     * dessiner le GameOver. Si la partie est gagnee, on ordonne au jpanel de
     * dessiner le LevelDone, puis on cree une thread mettant en pause toutes
     * les thread du jeu (y compris celle-ci) puis elle change le niveau.
     */
    public void run() {
	while (!this.canceled) {
	    if (this.balls.isEmpty()) {
		synchronized (this.jpanel) {
		    this.jpanel.setDead(true);
		}
		this.jframe.repaint();
		this.jframe.pauseGame();
	    } else if (this.bricks.isEmpty()) {
		synchronized (this.jpanel) {
		    this.jpanel.setWinner(true);
		}
		Thread tmp = new Thread() {
		    public void run() {
			EndGameDetection.this.jframe.pauseGame();
			EndGameDetection.this.jframe.repaint();
			try {
			    Thread.sleep(1000L);
			} catch (InterruptedException e) {
			    e.printStackTrace();
			}
			EndGameDetection.this.gameInfo.levelDone();
		    }
		};
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
