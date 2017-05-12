package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import object.Ball;
import object.Brick;
import object.Paddle;
import object.Particule;
import object.Score;

public class MyJPanel extends JPanel {

    private List<Ball> balls;
    private List<Brick> bricks;
    private Paddle paddle;
    private Score score;
    private List<Particule> points;

    private boolean isDead;
    private boolean isWinner;

    private Image gameOverImage;
    private Image levelDone;
    private Image background;

    /**
     * 
     */
    public MyJPanel() {
	super();
    }

    /**
     * 
     * @param balls
     * @param bricks
     * @param paddle
     */
    public void init(List<Ball> balls, List<Brick> bricks, Paddle paddle, Score score,
	List<Particule> points) {
	this.balls = balls;
	this.bricks = bricks;
	this.paddle = paddle;
	this.isDead = false;
	this.isWinner = false;
	this.score = score;
	this.points = points;
	try {
	    this.gameOverImage = ImageIO.read(new File("./res/game_over.png"));
	    this.levelDone = ImageIO.read(new File("./res/level_done.png"));
	    this.background = new Random(System.currentTimeMillis()).nextBoolean() ? ImageIO.read(new File("./res/wall_1.jpg")) : ImageIO.read(new File("./res/wall_2.jpg"));
	} catch (IOException e) {
	    e.printStackTrace();
	}
	System.out.println("JPANEL INIT");
    }

    /**
     * 
     * @param isDead
     */
    public void setDead(boolean isDead) {
	this.isDead = isDead;
    }

    /**
     * 
     * @param isWinner
     */
    public void setWinner(boolean isWinner) {
	this.isWinner = isWinner;
    }

    /**
     * 
     */
    public void paintComponent(Graphics g) {
	Graphics2D g2 = (Graphics2D) g;
	g2.setBackground(new Color(189, 195, 199));
	g2.clearRect(0, 0, super.getWidth(), super.getHeight());

	/*g2.setColor(new Color(236, 240, 241));
	g2.fillRect(0, (int) (0.969 * this.getHeight()), this.getWidth(), (int) (0.016 * this
		.getHeight()));*/

	g2.drawImage(this.background, 0, 0, this.getWidth(), super.getHeight(), null);
	
	if (this.isDead) {
	    g2.drawImage(this.gameOverImage, 0, 0, this.getWidth(), super.getHeight(), null);
	} else if (this.isWinner) {
	    g2.drawImage(this.levelDone, (int) (0.1 * this.getWidth()), (int) (0.1 * this
		    .getHeight()), (int) (0.8 * this.getWidth()), (int) (0.8 * this.getHeight()),
		    null);
	} else {
	    this.score.draw(g2);
	    for (int i = 0; i < this.balls.size(); i++)
		this.balls.get(i).draw(g2);
	    for (int i = 0; i < this.bricks.size(); i++)
		this.bricks.get(i).draw(g2);
	    synchronized (this.points) {
		for (int i = this.points.size() - 1; i >= 0; i--)
		    this.points.get(i).draw(g2);
	    }
	    this.paddle.draw(g2);
	}
    }
}
