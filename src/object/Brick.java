package object;

import game.MyJPanel;

public class Brick extends Item {
    
    private static float width = 0.06f;
    private static float height = 0.02f;

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
    
    public float getBrickW() {
	return width;
    }
    
    public float getBrickH() {
	return height;
    }

}
