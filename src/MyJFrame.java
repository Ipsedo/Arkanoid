import java.awt.BorderLayout;
import java.util.Random;

import javax.swing.JFrame;

import object.Ball;

public class MyJFrame extends JFrame {

	public static final int WIDTH = 500;
	public static final int HEIGHT = 500;
	
	private MyJPanel jPanel;
	
	private Ball ball;
	
	public MyJFrame(){
		super("Arkanoid");
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setSize(MyJFrame.WIDTH, MyJFrame.HEIGHT);
		super.setLocation(200, 200);
		super.getContentPane().setLayout(new BorderLayout());
		
		this.jPanel = new MyJPanel();
		this.ball = new Ball(new Random(System.currentTimeMillis()), 0, this.jPanel.getWidth(), 0, this.jPanel.getHeight());
		
		super.getContentPane().add(this.jPanel, BorderLayout.CENTER);
		super.setVisible(true);
	}
	
	public static void main(String[] args) {
		MyJFrame frame = new MyJFrame();
	}
}
