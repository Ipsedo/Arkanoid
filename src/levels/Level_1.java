package levels;

import java.awt.Graphics2D;
import java.util.ArrayList;

import object.Ball;
import object.Brick;
import object.Paddle;
import util.Level;

public class Level_1 implements Level {

    private Paddle paddle;
    private ArrayList<Ball> balls;
    private ArrayList<Brick> bricks;

    @Override
    public void init(Paddle paddle, ArrayList<Ball> balls, ArrayList<Brick> bricks) {
	// TODO Auto-generated method stub
	this.paddle = paddle;
	this.balls = balls;
	this.bricks = bricks;
    }

    @Override
    public void draw(Graphics2D g2) {
	// TODO Auto-generated method stub
	for (Ball b : this.balls)
	    b.draw(g2);
	for (Brick b : this.bricks)
	    b.draw(g2);
	this.paddle.draw(g2);
    }

    @Override
    public boolean isDead() {
	// TODO Auto-generated method stub
	return this.balls.isEmpty();
    }

    @Override
    public boolean isWinner() {
	// TODO Auto-generated method stub
	return this.bricks.isEmpty();
    }

}
