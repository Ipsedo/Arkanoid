package object;

import game.MyJPanel;

public class Brick extends Item {

    /**
     * 
     * @param mPosition
     * @param mSpeed
     * @param mAcceleration
     * @param jpanel
     */
    public Brick(float[] mPosition, float[] mSpeed, float[] mAcceleration, MyJPanel jpanel) {
	super(mPosition, mSpeed, mAcceleration, 0.02f, 0.01f, jpanel);
    }

}
