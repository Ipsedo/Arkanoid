package object;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import game.MyJPanel;

public class Item {
	
	public enum CollisionSide { LEFT, RIGHT, DOWN, UP, NONE }

	protected Random rand;
	private MyJPanel jpanel;
	
	protected float[] mPosition;
	protected float[] mSpeed;
	protected float[] mAcceleration;
	
	protected float width;
	protected float height;
	
	protected Rectangle2D rect;
	
	protected Color color;
	
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
	
	public boolean intersect(Item other) {
		return this.rect.intersects(other.rect);
	}
	
	protected CollisionSide detectCollisionSide(Item other) {
		float dx = (this.mPosition[0] + this.width / 2f) - (other.mPosition[0] + other.width / 2f);
	    float dy = (this.mPosition[1] + this.height / 2f) - (other.mPosition[1] + other.height / 2f);
	    float avWidth = (this.width + other.width) / 2f;
	    float avHeight = (this.height + other.height) / 2f;
	    float crossWidth = avWidth * dy;
	    float crossHeight = avHeight * dx;
	    CollisionSide collision = CollisionSide.NONE;
	    if(Math.abs(dx) <= avWidth && Math.abs(dy) <= avHeight) {
	        if(crossWidth > crossHeight){
	            collision = (crossWidth>(-crossHeight)) ? CollisionSide.DOWN : CollisionSide.LEFT;
	        } else {
	            collision = (crossWidth>-(crossHeight)) ? CollisionSide.RIGHT : CollisionSide.UP;
	        }
	    }
	    return collision;
	}
	
	protected void changeSpeed(CollisionSide collisionSide) {
		switch(collisionSide) {
		case UP:
			this.mSpeed[1] = -Math.abs(this.mSpeed[1]);
			break;
		case DOWN:
			this.mSpeed[1] = Math.abs(this.mSpeed[1]);
			break;
		case LEFT:
			this.mSpeed[0] = -Math.abs(this.mSpeed[0]);
			break;
		case RIGHT:
			this.mSpeed[0] = Math.abs(this.mSpeed[0]);
			break;
		case NONE:
			break;
		}
	}
	
	public void collide(Item other) {
		CollisionSide side = this.detectCollisionSide(other);
		this.changeSpeed(side);
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
		g2.fillRect((int) (this.mPosition[0] * (float) this.jpanel.getWidth()), (int) (this.mPosition[1] * (float) this.jpanel.getHeight()), (int) (this.width * (float) this.jpanel.getWidth()), (int) (this.height * (float) this.jpanel.getHeight()));
	}
}
