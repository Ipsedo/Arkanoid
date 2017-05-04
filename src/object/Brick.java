package object;
import game.MyJPanel;

public class Brick extends Item{
	
	public Brick(float[] mPosition, float[] mSpeed, float[] mAcceleration, int width, int height, MyJPanel jpanel) {
		super(mPosition, mSpeed, mAcceleration, width, height, jpanel);
	}

}
