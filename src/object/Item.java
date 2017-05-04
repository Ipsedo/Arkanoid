package object;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import game.MyJPanel;

public class Item {
	
	public enum CollisionSide { HORIZOBNTAL, VERTICAL }

	protected Random rand;
	private MyJPanel jpanel;
	
	protected float[] mPosition;
	protected float[] mSpeed;
	protected float[] mAcceleration;
	
	protected int width;
	protected int height;
	
	private Rectangle2D rect;
	
	protected Color color;
	
	public Item(float[] mPosition, float[] mSpeed, float[] mAcceleration, int width, int height, MyJPanel jpanel) {
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
	
	public void updateScreenSize(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public boolean intersect(Item other) {
		return this.rect.intersects(other.rect);
	}
	
	public CollisionSide detectCollisionSide(Item other){
		return null;
	}
	
	public void move(float[] acceleration){
		this.mAcceleration = acceleration.clone();
		this.mSpeed[0] += this.mAcceleration[0];
		this.mSpeed[1] += this.mAcceleration[1];
		
		this.mPosition[0] += this.mSpeed[0];
		this.mPosition[1] += this.mSpeed[1];
		
		this.rect = new Rectangle2D.Float(this.mPosition[0], this.mPosition[1], this.width, this.height);
	}
	
	public int getScreenWidth(){
		return this.jpanel.getWidth();
	}
	
	public int getScreenHeight(){
		return this.jpanel.getHeight();
	}
	
	public void draw(Graphics2D g2){
		g2.setColor(this.color);
		g2.fillRect((int) this.mPosition[0], (int) this.mPosition[1], this.width, this.height);
	}
}
