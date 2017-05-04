package game;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;

import object.Ball;
import object.Paddle;
import thread.BallsBounding;
import thread.BallsMove;
import thread.PaddleBounding;
import thread.PaddleMove;

public class MyJFrame extends JFrame implements Runnable {

	public static final int WIDTH = 500;
	public static final int HEIGHT = 500;
	
	private MyJPanel jPanel;
	
	private ArrayList<Ball> balls;
	private Paddle paddle;
	
	private boolean closed;
	
	private BallsMove ballsMoveThread;
	private BallsBounding ballsBoundingThread;
	private PaddleMove paddleMoveThread;
	private PaddleBounding paddleBoundingThread;
	
	public MyJFrame(){
		super("Arkanoid");
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setLocation(200, 200);
		//super.setResizable(false);
		super.getContentPane().setLayout(new BorderLayout());
		super.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                MyJFrame.this.closed = true;
                MyJFrame.this.ballsMoveThread.setCancel(true);
                MyJFrame.this.ballsBoundingThread.setCancel(true);
                MyJFrame.this.paddleMoveThread.setCancel(true);
                MyJFrame.this.paddleBoundingThread.setCancel(true);
                e.getWindow().dispose();
            }
        });
		this.closed = false;
		
		this.jPanel = new MyJPanel();
		super.getContentPane().add(this.jPanel, BorderLayout.CENTER);
		
		super.pack();
		super.setSize(MyJFrame.WIDTH, MyJFrame.HEIGHT);
		
		this.balls = new ArrayList<>();
		Random rand = new Random(System.currentTimeMillis());
		this.balls.add(new Ball(rand, this.jPanel));
		this.balls.add(new Ball(rand, this.jPanel));
		this.balls.add(new Ball(rand, this.jPanel));
		this.balls.add(new Ball(rand, this.jPanel));
		this.balls.add(new Ball(rand, this.jPanel));
		this.balls.add(new Ball(rand, this.jPanel));

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
		
		Thread mainLoop = new Thread(this);
		mainLoop.start();
		
		this.ballsMoveThread = new BallsMove(this.balls);
		this.ballsMoveThread.start();
		this.ballsBoundingThread = new BallsBounding(this.balls);
		this.ballsBoundingThread.start();
		this.paddleMoveThread = new PaddleMove(this.paddle);
		this.paddleMoveThread.start();
		this.paddleBoundingThread = new PaddleBounding(this.balls, this.paddle);
		this.paddleBoundingThread.start();
		
		this.jPanel.init(this.balls, this.paddle);
		
		super.getContentPane().add(new GameInfoJPanel(), BorderLayout.EAST);
		
		super.setVisible(true);
	}
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(!this.closed) {
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