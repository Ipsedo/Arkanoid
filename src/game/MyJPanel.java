package game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
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
	
	private Image gameOverImage;
	private Image levelDone;

	public MyJPanel(){
		super();
	}
	
	public void init(ArrayList<Ball> balls, ArrayList<Brick> bricks, Paddle paddle) {
		this.balls = balls;
		this.bricks = bricks;
		this.paddle = paddle;
		this.isDead = false;
		this.isWinner = false;
		try {
			this.gameOverImage = ImageIO.read(new File("game_over.png"));
			this.levelDone = ImageIO.read(new File("level_done.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		if(this.isDead) {
			g2.drawImage(this.gameOverImage, 0, 0, this.getWidth(), super.getHeight(), null);
		} else if(this.isWinner) {
			g2.drawImage(this.levelDone, 0, 0, this.getWidth(), super.getHeight(), null);
		} else {
			for(int i = 0; i < this.balls.size(); i++)
				this.balls.get(i).draw(g2);
			for(int i = 0; i < this.bricks.size(); i++)
				this.bricks.get(i).draw(g2);
			this.paddle.draw(g2);
		}
	}
}
