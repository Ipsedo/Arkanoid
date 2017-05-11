package object;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;
import java.util.Random;

import game.MyJPanel;

public class Brick extends Item {

    public static float width = 1f / 20f;
    public static float height = 1f / 50f;

    private int life;

    private int isBonus;

    /**
     * 
     * @param mPosition
     * @param mSpeed
     * @param mAcceleration
     * @param jpanel
     * @param life
     * @param isBonus
     */
    private Brick(float[] mPosition, float[] mSpeed, float[] mAcceleration, MyJPanel jpanel,
	    int life, int isBonus) {
	super(mPosition, mSpeed, mAcceleration, width, height, jpanel);
	this.life = life;
	this.isBonus = isBonus;
    }

    /**
     * 
     * @param mPosition
     * @param mSpeed
     * @param mAcceleration
     * @param jpanel
     * @return
     */
    public static Brick makeBonusBrick(float[] mPosition, float[] mSpeed, float[] mAcceleration,
	    MyJPanel jpanel) {
	return new Brick(mPosition, mSpeed, mAcceleration, jpanel, 1, 1);
    }

    /**
     * 
     * @param mPosition
     * @param mSpeed
     * @param mAcceleration
     * @param jpanel
     * @param life
     * @return
     */
    public static Brick makeSimpleBrick(float[] mPosition, float[] mSpeed, float[] mAcceleration,
	    MyJPanel jpanel, int life) {
	return new Brick(mPosition, mSpeed, mAcceleration, jpanel, life, 0);
    }

    public boolean intersect(Item other) {
	if (this.rect.intersects(other.rect)) {
	    this.life--;
	    return true;
	}
	return false;
    }

    public int getScore() {
	return (this.life + 1) * (this.life + 1) * 5;
    }

    public boolean isAlive() {
	return this.life > 0;
    }

    public void updateBalls(List<Ball> balls, MyJPanel jpanel, Random rand) {
	if (this.isBonus == 3) {
	    balls.add(new Ball(rand, jpanel));
	}
    }

    public void updatePaddle(Paddle paddle) {
	if (this.isBonus == 1) {
	    paddle.width += 0.05f;
	} else if (this.isBonus == 2) {
	    paddle.width -= 0.05f;
	}
    }

    public void draw(Graphics2D g2) {
	if (this.life == 3) {
	    g2.setColor(new Color(192, 57, 43));
	} else if (this.life == 2) {
	    g2.setColor(new Color(211, 84, 0));
	} else if (this.life == 1) {
	    g2.setColor(new Color(243, 156, 18));
	}
	super.draw(g2);

	// Bonus ball++
	if (this.isBonus == 3) {
	    g2.setColor(Color.BLACK);
	    g2.fillOval((int) ((super.mPosition[0] + super.width / 2f - super.height / 2)
		    * (float) super.getScreenWidth()), (int) (super.mPosition[1]
			    * (float) super.getScreenHeight()), (int) (super.height
				    * (float) super.getScreenWidth()), (int) (super.height
					    * (float) super.getScreenHeight()));
	}
	// Bonus paddle size++
	else if (this.isBonus == 1) {
	    g2.setColor(Color.GREEN);
	    g2.fillRect((int) ((super.mPosition[0] + 9 * super.width / 20f) * (float) super.getScreenWidth()), 
		    	(int) ((super.mPosition[1] + 4 * super.height / 10f) * (float) super.getScreenHeight()) + 1,
		    	(int) (super.width / 10 * (float) super.getScreenWidth()), 
		    	(int) (super.height * (float) super.getScreenHeight()) - 1);
	    
	    g2.fillRect((int) ((super.mPosition[0] + 2 * super.width / 8) * (float) super.getScreenWidth()), 
		    	(int) ((super.mPosition[1] + 4 * super.height / 10f) * (float) super.getScreenHeight()), 
		    	(int) (super.width / 2 * (float) super.getScreenWidth()), 
		    	(int) (super.height / 5 * (float) super.getScreenHeight()));
	}
	// Bonus paddle size--
	else if (this.isBonus == 1) {

	}
    }

    public String toString() {
	return super.mPosition[0] + " " + super.mPosition[1] + " " + this.life + " " + Integer
		.toString(this.isBonus);
    }
}
