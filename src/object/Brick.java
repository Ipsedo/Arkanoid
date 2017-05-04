package object;
import java.awt.geom.Rectangle2D;

public class Brick extends Item{
	
	public Brick(float[] mPosition, float[] mSpeed, float[] mAcceleration, int width, int height, int screenWidth, int screenHeight) {
		super(mPosition, mSpeed, mAcceleration, width, height, screenWidth, screenHeight);
	}

}
