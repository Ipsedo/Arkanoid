package object;

import game.MyJPanel;

import java.awt.Color;
import java.awt.Graphics2D;

public class Score {
    
    private int currentScore;
    private MyJPanel jpanel;
    
    public Score(MyJPanel jpanel) {
	this.currentScore = 0;
	this.jpanel = jpanel;
    }
    
    public void incrScore(int score) {
	this.currentScore += score;
    }
    
    public void reset() {
	this.currentScore = 0;
    }
    
    public void draw(Graphics2D g2) {
	g2.setColor(new Color(44, 62, 80));
	g2.drawChars(("" + this.currentScore).toCharArray(), 0, ("" + this.currentScore).toCharArray().length, (int) (0.5 * this.jpanel.getWidth()), (int) (0.8 * this.jpanel.getHeight()));
    }

}
