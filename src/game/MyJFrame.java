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
	
	public MyJFrame(){
		super("Arkanoid");
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setSize(MyJFrame.WIDTH, MyJFrame.HEIGHT);
		super.setLocation(200, 200);
		super.setResizable(false);
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
                e.getWindow().dispose();
            }
        });
		this.closed = false;
		this.balls = new ArrayList<>();
		this.balls.add(new Ball(new Random(System.currentTimeMillis()), 0, MyJPanel.WIDTH, 0, MyJPanel.HEIGHT));
		this.paddle = new Paddle();
		
		this.jPanel = new MyJPanel(this.balls, this.paddle);
		this.jPanel.addMouseMotionListener(new MouseMotionListener(){

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				MyJFrame.this.paddle.setPos(e.getX());
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
		
		super.getContentPane().add(this.jPanel, BorderLayout.CENTER);
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
