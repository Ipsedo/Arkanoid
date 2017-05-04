package object;
import java.awt.geom.Rectangle2D;

public class Item {

	protected int screenWidth;
	protected int screenHeight;
	
	protected float[] mPosition;
	protected float[] mSpeed;
	protected float[] mAcceleration;
	
	protected int width;
	protected int height;
	
	private Rectangle2D rect;
	
	public Item(float[] mPosition, float[] mSpeed, float[] mAcceleration, int width, int height, int screenWidth, int screenHeight) {
		this.mPosition = mPosition.clone();
		this.mSpeed = mSpeed.clone();
		this.mAcceleration = mAcceleration.clone();
		this.width = width;
		this.height = height;
		this.rect = new Rectangle2D.Float(this.mPosition[0], this.mPosition[1], this.width, this.height);
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
	}
	
	public synchronized void updateScreenSize(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public boolean intersect(Item other) {
		return this.rect.intersects(other.rect);
	}
	
	public void move(float[] acceleration){
		this.mAcceleration = acceleration.clone();
		this.mSpeed[0] += this.mAcceleration[0];
		this.mSpeed[1] += this.mAcceleration[1];
		
		this.mPosition[0] += this.mSpeed[0];
		this.mPosition[1] += this.mSpeed[1];
		
		this.rect = new Rectangle2D.Float(this.mPosition[0], this.mPosition[1], this.width, this.height);
	}
}
