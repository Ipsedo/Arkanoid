package game;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;

import levels.LevelMaker;
import object.Ball;
import object.Brick;
import object.Paddle;
import thread.BallsBounding;
import thread.BallsBricksCollision;
import thread.BallsCollision;
import thread.BallsMove;
import thread.PaddleBounding;
import thread.PaddleMove;
import util.BrickInitializator;

public class MyJFrame extends JFrame implements Runnable {

	public static final int WIDTH = 500;
	public static final int HEIGHT = 500;
	
	private MyJPanel jPanel;
	
	private ArrayList<Ball> balls;
	private ArrayList<Brick> bricks;
	private Paddle paddle;
	
	private Thread mainLoop;
	private boolean closed;
	
	private BallsMove ballsMoveThread;
	private BallsBounding ballsBoundingThread;
	private PaddleMove paddleMoveThread;
	private PaddleBounding paddleBoundingThread;
	private BallsCollision ballsCollisionThread;
	private BallsBricksCollision ballsBricksCollisionThread;
	
	public MyJFrame(){
		super("Arkanoid");
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setLocation(200, 200);
		super.getContentPane().setLayout(new BorderLayout());
		super.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                MyJFrame.this.killThreads();
                e.getWindow().dispose();
            }
        });
		
		this.jPanel = new MyJPanel();
		super.getContentPane().add(this.jPanel, BorderLayout.CENTER);
		
		super.pack();
		super.setSize(MyJFrame.WIDTH, MyJFrame.HEIGHT);
		
		this.balls = new ArrayList<>();
		Random rand = new Random(System.currentTimeMillis());
		for(int i = 0; i < 20; i++) {
			this.balls.add(new Ball(rand, this.jPanel));
		}
		
		this.bricks = BrickInitializator.initBrickRandom(this.jPanel, 5);

		this.paddle = new Paddle(this.jPanel);
		
		this.jPanel.addMouseMotionListener(new MouseMotionListener(){

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				synchronized(MyJFrame.this.paddle) {
					MyJFrame.this.paddle.setPos(e.getX());
				}
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				synchronized(MyJFrame.this.paddle) {
					MyJFrame.this.paddle.setPos(e.getX());
				}
			}
			
		});
		
		this.initThreads();
		
		this.jPanel.init(this.balls, this.bricks, this.paddle);
		
		super.getContentPane().add(new GameInfoJPanel(this), BorderLayout.EAST);
		
		super.setVisible(true);
	}
	
	private void initThreads(){
		this.closed = false;
		this.mainLoop = new Thread(this);
		this.mainLoop.start();
		this.ballsMoveThread = new BallsMove(this.balls);
		this.ballsMoveThread.start();
		this.ballsBoundingThread = new BallsBounding(this.balls);
		this.ballsBoundingThread.start();
		this.paddleMoveThread = new PaddleMove(this.paddle);
		this.paddleMoveThread.start();
		this.paddleBoundingThread = new PaddleBounding(this.balls, this.paddle);
		this.paddleBoundingThread.start();
		this.ballsCollisionThread = new BallsCollision(this.balls);
		this.ballsCollisionThread.start();
		this.ballsBricksCollisionThread = new BallsBricksCollision(this.balls, this.bricks);
		this.ballsBricksCollisionThread.start();
	}
	
	public void killThreads() {
		this.closed = true;
		this.ballsBricksCollisionThread.setCancel(true);
		this.ballsMoveThread.setCancel(true);
		this.ballsBoundingThread.setCancel(true);
		this.paddleMoveThread.setCancel(true);
		this.paddleBoundingThread.setCancel(true);
		this.ballsCollisionThread.setCancel(true);
		
		
		try {
			this.ballsBricksCollisionThread.join();
			this.mainLoop.join();
			this.paddleMoveThread.join();
			this.ballsCollisionThread.join();
			this.ballsBoundingThread.join();
			this.paddleBoundingThread.join();
			this.ballsMoveThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void resumeGame(){
		this.initThreads();
	}
	
	public void resetGame() {
		this.killThreads();
		
		synchronized (this.bricks) {
			this.bricks.clear();
			this.bricks.addAll(LevelMaker.getBricksFromLevelID(0, this.jPanel));
		}
		
		synchronized (this.balls) {
			this.balls.clear();
			this.balls.addAll(LevelMaker.getBallsFromLevelId(0, new Random(System.currentTimeMillis()), this.jPanel));
		}
		
		this.initThreads();
	}
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(!this.closed) {
			Toolkit.getDefaultToolkit().sync();
			this.jPanel.repaint();
			try {
				Thread.sleep(20L);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		MyJFrame frame = new MyJFrame();
	}
}