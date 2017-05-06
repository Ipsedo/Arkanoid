package util;

import java.awt.Graphics2D;
import java.util.ArrayList;

import object.Ball;
import object.Brick;
import object.Paddle;

public interface Level {
    void init(Paddle paddle, ArrayList<Ball> balls, ArrayList<Brick> bricks);

    void draw(Graphics2D g2);

    boolean isDead();

    boolean isWinner();
}
