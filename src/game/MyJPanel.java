package game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import object.Ball;
import object.Brick;
import object.Paddle;

public class MyJPanel extends JPanel {
	
	private ArrayList<Ball> balls;
	private ArrayList<Brick> bricks;
	private Paddle paddle;
	
	private boolean isDead;
	private boolean isWinner;

	public MyJPanel(){
		super();
		this.isDead = false;
		this.isWinner = false;
	}
	
	public void init(ArrayList<Ball> balls, ArrayList<Brick> bricks, Paddle paddle) {
		this.balls = balls;
		this.bricks = bricks;
		this.paddle = paddle;
	}
	
	public void setDead(boolean isDead){
		this.isDead = isDead;
	}
	
	public void setWinner(boolean isWinner) {
		this.isWinner = isWinner;
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setBackground(Color.GRAY);
		g2.clearRect(0, 0, super.getWidth(), super.getHeight());
		for(Ball b : this.balls)
			b.draw(g2);
		for(Brick b : this.bricks)
			b.draw(g2);
		this.paddle.draw(g2);
	}
}
