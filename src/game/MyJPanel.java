package game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import object.Ball;

public class MyJPanel extends JPanel {
	
	public static int WIDTH;
	public static int HEIGHT;
	
	private ArrayList<Ball> balls;
	

	public MyJPanel(ArrayList<Ball> balls){
		super();
		MyJPanel.WIDTH = super.getWidth();
		MyJPanel.HEIGHT = super.getHeight();
		super.setSize(MyJPanel.WIDTH, MyJPanel.HEIGHT);
		this.balls = balls;
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setBackground(Color.GRAY);
		g2.clearRect(0, 0, super.getWidth(), super.getHeight());
		for(Ball b : this.balls)
			b.draw(g2);
	}
}
