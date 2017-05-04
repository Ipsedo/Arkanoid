package object;
import game.MyJPanel;

public class Brick extends Item {
	
	public Brick(float[] mPosition, float[] mSpeed, float[] mAcceleration, MyJPanel jpanel) {
		super(mPosition, mSpeed, mAcceleration, 20, 10, jpanel);
	}

}
