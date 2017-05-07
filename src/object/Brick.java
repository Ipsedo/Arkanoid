package object;

import game.MyJPanel;

public class Brick extends Item {
    
    public static float width = 1f / 20f;
    public static float height = 1f / 50f;

    /**
     * 
     * @param mPosition
     * @param mSpeed
     * @param mAcceleration
     * @param jpanel
     */
    public Brick(float[] mPosition, float[] mSpeed, float[] mAcceleration, MyJPanel jpanel) {
	super(mPosition, mSpeed, mAcceleration, width, height, jpanel);
    }
}
