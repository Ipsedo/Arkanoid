package game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import object.Ball;
import object.Paddle;

public class MyJPanel extends JPanel {
	
	public static int WIDTH = 400;
	public static int HEIGHT = 500;
	
	private ArrayList<Ball> balls;
	private Paddle paddle;
	

	public MyJPanel(ArrayList<Ball> balls, Paddle paddle){
		super();
		this.balls = balls;
		this.paddle = paddle;
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setBackground(Color.GRAY);
		g2.clearRect(0, 0, MyJPanel.WIDTH, MyJPanel.HEIGHT);
		for(Ball b : this.balls)
			b.draw(g2);
		this.paddle.draw(g2);
	}
}
