package object;

import game.MyJPanel;

import java.awt.Color;
import java.awt.Graphics2D;

public class Brick extends Item {

    public static float width = 1f / 20f;
    public static float height = 1f / 50f;

    private int life;

    private boolean isBonus;

    /**
     * 
     * @param mPosition
     * @param mSpeed
     * @param mAcceleration
     * @param jpanel
     * @param life
     * @param isBonus
     */
    private Brick(float[] mPosition, float[] mSpeed, float[] mAcceleration, MyJPanel jpanel, int life, boolean isBonus) {
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
    public static Brick makeBonusBrick(float[] mPosition, float[] mSpeed, float[] mAcceleration, MyJPanel jpanel) {
	return new Brick(mPosition, mSpeed, mAcceleration, jpanel, 1, true);
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
    public static Brick makeSimpleBrick(float[] mPosition, float[] mSpeed, float[] mAcceleration, MyJPanel jpanel, int life) {
	return new Brick(mPosition, mSpeed, mAcceleration, jpanel, life, false);
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

    public boolean isBonus() {
	return this.isBonus;
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

	if (this.isBonus) {
	    g2.setColor(Color.BLACK);
	    g2.fillOval((int) (super.mPosition[0] * (float) super.getScreenWidth()), (int) (super.mPosition[1] * (float) super.getScreenHeight()), (int) (super.width * (float) super.getScreenWidth()), (int) (super.height * (float) super.getScreenHeight()));
	}
    }

    public String toString() {
	return super.mPosition[0] + " " + super.mPosition[1] + " " + this.life + " " + Boolean.toString(this.isBonus);
    }
}
