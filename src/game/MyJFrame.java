package game;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import levels.LevelMaker;
import object.Ball;
import object.Brick;
import object.Paddle;
import object.Particule;
import object.Score;
import thread.BallsBounding;
import thread.BallsBricksCollision;
import thread.BallsCollision;
import thread.BallsMove;
import thread.CancelableThread;
import thread.EndGameDetection;
import thread.ExplosionAction;
import thread.PaddleAction;
import thread.PaddleMove;

public class MyJFrame extends JFrame implements Runnable {

    public static final int WIDTH = 900;
    public static final int HEIGHT = 800;

    private MyJPanel jPanel;
    private GameInfoJPanel gameInfo;

    private List<Ball> balls;
    private List<Brick> bricks;
    private List<Particule> points;
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
    private ExplosionAction pointsActionThread;

    /**
     * Fenetre principale du jeu
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
	super.setSize(MyJFrame.WIDTH, MyJFrame.HEIGHT);
	this.setIconImage(new ImageIcon("./res/icone_1.png").getImage());

	// On appelle la creation de niveau depuis la classe LevelMaker
	this.balls = LevelMaker.getBallsFromLevelId(0, new Random(System.currentTimeMillis()),
		this.jPanel);
	this.bricks = LevelMaker.getBricksFromLevelID(0, this.jPanel);
	this.points = Collections.synchronizedList(new ArrayList<Particule>());

	this.paddle = new Paddle(this.jPanel);
	this.score = new Score(this.jPanel);

	// Ecoute l'action de la sourie sur le JPanel du jeu et affecte la
	// nouvelle postion de la raquette
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

	this.jPanel.init(this.balls, this.bricks, this.paddle, this.score, this.points);

	Toolkit.getDefaultToolkit().sync();
	this.jPanel.repaint();

	this.gameInfo = new GameInfoJPanel(this, this.myIdLevel);
	super.getContentPane().add(this.gameInfo, BorderLayout.EAST);
	super.setVisible(true);
    }

    /**
     * Remet le score a zero
     */
    public void clearScore() {
	synchronized (this.score) {
	    this.score.reset();
	}
    }

    /**
     * Divise le score par deux
     */
    public void divScore() {
	synchronized (this.score) {
	    this.score.divByTwo();
	}
    }

    /**
     * Initialisation des Threads du jeu
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
	this.ballsBricksCollisionThread = new BallsBricksCollision(this.balls, this.bricks,
		this.score, this.jPanel, this.paddle, this.points);
	this.ballsBricksCollisionThread.start();
	this.endGameDetectionThread = new EndGameDetection(this.balls, this.bricks, this.jPanel,
		this, this.gameInfo);
	this.endGameDetectionThread.start();
	this.pointsActionThread = new ExplosionAction(this.points);
	this.pointsActionThread.start();

	System.out.println("INIT");
    }

    /**
     * Terminer toutes les Threads du jeu
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
	this.pointsActionThread.setCancel(true);

	try {
	    this.mainLoop.join();
	    this.ballsMoveThread.join();
	    this.ballsBoundingThread.join();
	    this.paddleBoundingThread.join();
	    this.paddleMoveThread.join();
	    this.ballsCollisionThread.join();
	    this.ballsBricksCollisionThread.join();
	    this.endGameDetectionThread.join();
	    this.pointsActionThread.join();
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	System.out.println("KILL");
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
     *            Identifiant du niveau que l'on souhaite afficher/lancer
     */
    public void level(int x) {
	this.myIdLevel = x;

	synchronized (this.bricks) {
	    this.bricks.clear();
	    this.bricks.addAll(LevelMaker.getBricksFromLevelID(x, this.jPanel));
	}
	synchronized (this.balls) {
	    this.balls.clear();
	    this.balls.addAll(LevelMaker.getBallsFromLevelId(x, new Random(System
		    .currentTimeMillis()), this.jPanel));
	}

	synchronized (this.paddle) {
	    this.paddle.resetPaddle();
	}

	synchronized (this.points) {
	    this.points.clear();
	}

	synchronized (CancelableThread.class) {
	    CancelableThread.TIME_TO_WAIT = 5f;
	}

	this.jPanel.init(this.balls, this.bricks, this.paddle, this.score, this.points);
	this.jPanel.repaint();
    }

    /**
     * Creation du niveau en mode 'Edit' a partir du fichier choisi
     * 
     * @param fileName Nom du fichier
     */
    public void startLevelFromFile(String fileName) {

	synchronized (this.bricks) {
	    this.bricks.clear();
	    this.bricks.addAll(LevelMaker.createFromFile(fileName, this.jPanel));
	}
	synchronized (this.balls) {
	    this.balls.clear();
	    this.balls.addAll(LevelMaker.getBallsFromLevelId(3, new Random(System
		    .currentTimeMillis()), this.jPanel));
	}

	synchronized (CancelableThread.class) {
	    CancelableThread.TIME_TO_WAIT = 5f;
	}

	synchronized (this.points) {
	    this.points.clear();
	}

	this.jPanel.init(this.balls, this.bricks, this.paddle, this.score, this.points);
	this.jPanel.repaint();
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

    /**
     * Fonction main
     * 
     * @param args Inutilise dans le programme
     */
    public static void main(String[] args) {
	new MyJFrame();
    }
}