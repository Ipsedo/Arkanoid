package game;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;

import object.Ball;

public class MyJFrame extends JFrame {

	public static final int WIDTH = 500;
	public static final int HEIGHT = 500;
	
	private MyJPanel jPanel;
	
	private ArrayList<Ball> balls;
	
	public MyJFrame(){
		super("Arkanoid");
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setSize(MyJFrame.WIDTH, MyJFrame.HEIGHT);
		super.setLocation(200, 200);
		super.getContentPane().setLayout(new BorderLayout());
		
		this.balls = new ArrayList<>();
		this.balls.add(new Ball(new Random(System.currentTimeMillis()), 0, MyJPanel.WIDTH, 0, MyJPanel.HEIGHT));
		this.jPanel = new MyJPanel(this.balls);
		this.jPanel.repaint();
		
		super.getContentPane().add(this.jPanel, BorderLayout.CENTER);
		super.setVisible(true);
	}
	
	public static void main(String[] args) {
		MyJFrame frame = new MyJFrame();
	}
}
