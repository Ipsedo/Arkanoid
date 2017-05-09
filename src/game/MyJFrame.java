package game;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import levels.LevelMaker;
import object.Ball;
import object.Brick;
import object.Paddle;
import object.Score;
import thread.BallsBounding;
import thread.BallsBricksCollision;
import thread.BallsCollision;
import thread.BallsMove;
import thread.CancelableThread;
import thread.EndGameDetection;
import thread.PaddleAction;
import thread.PaddleMove;

public class MyJFrame extends JFrame implements Runnable {

    public static final int WIDTH = 900;
    public static final int HEIGHT = 800;

    private MyJPanel jPanel;
    private GameInfoJPanel gameInfo;

    private List<Ball> balls;
    private List<Brick> bricks;
    private Paddle paddle;
    private Score score;

    private Thread mainLoop;
    private boolean closed = true;
    private int myIdLevel;

    private BallsMove ballsMoveThread;
    private BallsBounding ballsBoundingThread;
    private PaddleMove paddleMoveThread;
    private PaddleAction paddleBoundingThread;
    private BallsCollision ballsCollisionThread;
    private BallsBricksCollision ballsBricksCollisionThread;
    private EndGameDetection endGameDetectionThread;

    /**
     * 
     */
    public MyJFrame() {
	super("Arkanoid");
	super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	super.setLocation(300, 0);
	super.getContentPane().setLayout(new BorderLayout());
	super.addWindowListener(new WindowAdapter() {
	    @Override
	    public void windowClosing(WindowEvent e) {
		if (!MyJFrame.this.closed) {
		    MyJFrame.this.killThreads();
		}
		e.getWindow().dispose();
	    }
	});

	this.myIdLevel = 0;

	this.jPanel = new MyJPanel();
	super.getContentPane().add(this.jPanel, BorderLayout.CENTER);

	super.pack();
	
	super.setSize(MyJFrame.WIDTH, MyJFrame.HEIGHT);

	this.setIconImage(new ImageIcon("icone_1.png").getImage());

	// On appelle la creation de niveau depuis la classe LevelMaker
	this.balls = LevelMaker.getBallsFromLevelId(0, new Random(System.currentTimeMillis()), this.jPanel);
	this.bricks = LevelMaker.getBricksFromLevelID(0, this.jPanel);

	this.paddle = new Paddle(this.jPanel);

	this.score = new Score(this.jPanel);

	this.jPanel.addMouseMotionListener(new MouseMotionListener() {

	    @Override
	    public void mouseDragged(MouseEvent e) {
		synchronized (MyJFrame.this.paddle) {
		    MyJFrame.this.paddle.setPos(e.getX());
		}
	    }

	    @Override
	    public void mouseMoved(MouseEvent e) {
		synchronized (MyJFrame.this.paddle) {
		    MyJFrame.this.paddle.setPos(e.getX());
		}
	    }

	});

	this.jPanel.init(this.balls, this.bricks, this.paddle, this.score);

	Toolkit.getDefaultToolkit().sync();
	this.jPanel.repaint();

	this.gameInfo = new GameInfoJPanel(this, this.myIdLevel);
	
	super.getContentPane().add(this.gameInfo, BorderLayout.EAST);

	super.setVisible(true);
    }

    /**
     * Initialisation des Threads de jeu
     */
    private void initThreads() {
	this.closed = false;
	this.mainLoop = new Thread(this);
	this.mainLoop.start();
	this.ballsMoveThread = new BallsMove(this.balls);
	this.ballsMoveThread.start();
	this.ballsBoundingThread = new BallsBounding(this.balls);
	this.ballsBoundingThread.start();
	this.paddleMoveThread = new PaddleMove(this.paddle);
	this.paddleMoveThread.start();
	this.paddleBoundingThread = new PaddleAction(this.balls, this.paddle);
	this.paddleBoundingThread.start();
	this.ballsCollisionThread = new BallsCollision(this.balls);
	this.ballsCollisionThread.start();
	this.ballsBricksCollisionThread = new BallsBricksCollision(this.balls, this.bricks, this.score);
	this.ballsBricksCollisionThread.start();
	this.endGameDetectionThread = new EndGameDetection(this.balls, this.bricks, this.jPanel, this, this.gameInfo);
	this.endGameDetectionThread.start();
    }

    /**
     * Terminer toutes les Threads de jeu
     */
    private void killThreads() {
	this.closed = true;
	this.ballsMoveThread.setCancel(true);
	this.ballsBoundingThread.setCancel(true);
	this.paddleMoveThread.setCancel(true);
	this.paddleBoundingThread.setCancel(true);
	this.ballsCollisionThread.setCancel(true);
	this.ballsBricksCollisionThread.setCancel(true);
	this.endGameDetectionThread.setCancel(true);

	try {
	    this.mainLoop.join();
	    this.ballsMoveThread.join();
	    this.ballsBoundingThread.join();
	    this.paddleBoundingThread.join();
	    this.paddleMoveThread.join();
	    this.ballsCollisionThread.join();
	    this.ballsBricksCollisionThread.join();
	    this.endGameDetectionThread.join();
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
    }

    /**
     * Reprendre la partie
     */
    public void resumeGame() {
	if (this.closed) {
	    this.initThreads();
	}
    }

    /**
     * Mettre en pause la partie
     */
    public void pauseGame() {
	if (!this.closed) {
	    this.killThreads();
	}
    }

    /**
     * Creation de level
     * 
     * @param x
     */
    public void level(int x) {
	this.myIdLevel = x;

	synchronized (this.bricks) {
	    this.bricks.clear();
	    this.bricks.addAll(LevelMaker.getBricksFromLevelID(x, this.jPanel));
	}
	synchronized (this.balls) {
	    this.balls.clear();
	    this.balls.addAll(LevelMaker.getBallsFromLevelId(x, new Random(System.currentTimeMillis()), this.jPanel));
	}

	/*
	 * synchronized (this.score) { this.score.reset(); }
	 */

	synchronized (CancelableThread.class) {
	    CancelableThread.TIME_TO_WAIT = 5f;
	}

	this.jPanel.init(this.balls, this.bricks, this.paddle, this.score);

	this.jPanel.repaint();
	
	this.pauseGame();
    }

    /**
     * Thread du rendu graphique
     */
    @Override
    public void run() {
	while (!this.closed) {
	    Toolkit.getDefaultToolkit().sync();
	    this.jPanel.repaint();
	    try {
		Thread.sleep((long) CancelableThread.TIME_TO_WAIT);
	    } catch (InterruptedException ie) {
		ie.printStackTrace();
	    }
	}
    }

    public static void main(String[] args) {
	MyJFrame frame = new MyJFrame();
    }
}