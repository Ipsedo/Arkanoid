package object;
import java.awt.geom.Rectangle2D;

public class Item {

	
	private float[] mPosition;
	private float[] mSpeed;
	private float[] mAcceleration;
	
	private int width;
	private int height;
	
	private Rectangle2D rect;
	
	public Item(float[] mPosition, float[] mSpeed, float[] mAcceleration, int width, int height) {
		this.mPosition = mPosition.clone();
		this.mSpeed = mSpeed.clone();
		this.mAcceleration = mAcceleration.clone();
		this.width = width;
		this.height = height;
		this.rect = new Rectangle2D.Float(this.mPosition[0], this.mPosition[1], this.width, this.height);
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
