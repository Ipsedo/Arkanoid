package object;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import game.MyJPanel;

public class Item {

    public enum CollisionSide {
	LEFT, RIGHT, DOWN, UP, NONE
    }

    protected Random rand;
    private MyJPanel jpanel;

    protected float[] mPosition;
    protected float[] mSpeed;
    protected float[] mAcceleration;

    protected float width;
    protected float height;

    protected Rectangle2D rect;

    public Color color;

    /**
     * 
     * @param mPosition
     * @param mSpeed
     * @param mAcceleration
     * @param width
     * @param height
     * @param jpanel
     */
    public Item(float[] mPosition, float[] mSpeed, float[] mAcceleration, float width, float height, MyJPanel jpanel) {
	this.mPosition = mPosition.clone();
	this.mSpeed = mSpeed.clone();
	this.mAcceleration = mAcceleration.clone();
	this.width = width;
	this.height = height;
	this.rect = new Rectangle2D.Float(this.mPosition[0], this.mPosition[1], this.width, this.height);
	this.jpanel = jpanel;
	this.color = Color.RED;
	this.rand = new Random(System.currentTimeMillis());
    }

    /**
     * 
     * @param other
     * @return
     */
    public boolean intersect(Item other) {
	return this.rect.intersects(other.rect);
    }

    /**
     * 
     * @param other
     */
    public void collide(Item other) {
	/**
	 * CollisionSide side = this.detectCollisionSide(other);
	 * this.changeSpeed(side);
	 **/
	if (other.rect.intersectsLine(this.mPosition[0], this.mPosition[1], this.mPosition[0] + this.width, this.mPosition[1])) {
	    other.mSpeed[1] = -Math.abs(other.mSpeed[1]);
	}
	if (other.rect.intersectsLine(this.mPosition[0], this.mPosition[1], this.mPosition[0], this.mPosition[1] + this.height)) {
	    other.mSpeed[0] = -Math.abs(other.mSpeed[0]);
	}
	if (other.rect.intersectsLine(this.mPosition[0] + this.width, this.mPosition[1], this.mPosition[0] + this.width, this.mPosition[1] + this.height)) {
	    other.mSpeed[0] = Math.abs(other.mSpeed[0]);
	}
	if (other.rect.intersectsLine(this.mPosition[0], this.mPosition[1] + this.height, this.mPosition[0] + this.width, this.mPosition[1] + this.height)) {
	    other.mSpeed[1] = Math.abs(other.mSpeed[1]);
	}
    }

    /**
     * 
     * @param acceleration
     */
    public void move(float[] acceleration) {
	this.mAcceleration = acceleration.clone();
	this.mSpeed[0] += this.mAcceleration[0];
	this.mSpeed[1] += this.mAcceleration[1];

	this.mPosition[0] += this.mSpeed[0];
	this.mPosition[1] += this.mSpeed[1];

	this.rect = new Rectangle2D.Float(this.mPosition[0], this.mPosition[1], this.width, this.height);
    }

    /**
     * 
     * @return
     */
    public int getScreenWidth() {
	return this.jpanel.getWidth();
    }

    /**
     * 
     * @return
     */
    public int getScreenHeight() {
	return this.jpanel.getHeight();
    }

    /**
     * 
     * @param g2
     */
    public void draw(Graphics2D g2) {
	g2.fillRect((int) (this.mPosition[0] * (float) this.jpanel.getWidth()), (int) (this.mPosition[1] * (float) this.jpanel.getHeight()), (int) (this.width * (float) this.jpanel.getWidth()), (int) (this.height * (float) this.jpanel.getHeight()));
	g2.setColor(Color.BLACK);
	g2.drawRect((int) (this.mPosition[0] * (float) this.jpanel.getWidth()), (int) (this.mPosition[1] * (float) this.jpanel.getHeight()), (int) (this.width * (float) this.jpanel.getWidth()), (int) (this.height * (float) this.jpanel.getHeight()));
    }
}
