package object;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import game.MyJPanel;

public class Ball extends Item {

    private static float maxSpeed = 0.003f;
    private static float size = 0.02f;

    /**
     * 
     * @param rand 
     * @param jpanel
     */
    public Ball(Random rand, MyJPanel jpanel) {
	super(new float[] { 0.5f, 0.7f }, Ball.initSpeed(rand), new float[] { 0f, 0f }, size, size, jpanel);
	super.color = new Color(44, 62, 80);
    }

    /**
     * 
     * @param mPosition
     * @param mSpeed
     * @param mAcceleration
     * @param jpanel
     */
    public Ball(float[] mPosition, float[] mSpeed, float[] mAcceleration, MyJPanel jpanel) {
	super(mPosition.clone(), mSpeed.clone(), mAcceleration.clone(), size, size, jpanel);
    }

    /**
     * On fait rebondir la balle et on renvoie faux si la balle est sortie (faux sinon)
     * @return
     */
    public boolean bounding() {
	if (super.mPosition[0] <= 0) {
	    super.mSpeed[0] = Math.abs(super.mSpeed[0]);
	}
	if (super.mPosition[1] <= 0) {
	    super.mSpeed[1] = Math.abs(super.mSpeed[1]);
	}
	if (super.mPosition[0] + super.width >= 1f) {
	    super.mSpeed[0] = -Math.abs(super.mSpeed[0]);
	}
	if (super.mPosition[1] >= 1f) {
	    return true;
	}
	return false;
    }

    /**
     * 
     * @param rand
     * @return
     */
    public static float[] initSpeed(Random rand) {
	double angle = rand.nextDouble() * Math.PI / 3d + Math.PI / 6d;
	return new float[] { (float) Math.cos(angle) * maxSpeed, (float) -Math.sin(angle) * maxSpeed};
    }

    /**
     * 
     */
    public void draw(Graphics2D g2) {
	g2.setColor(super.color);
	g2.fillOval((int) (this.mPosition[0] * (float) super.getScreenWidth()), (int) (this.mPosition[1] * (float) super.getScreenHeight()), (int) (this.width * (float) super.getScreenWidth()), (int) (this.height * (float) super.getScreenHeight()));
	g2.setColor(Color.BLACK);
	g2.drawOval((int) (this.mPosition[0] * (float) super.getScreenWidth()), (int) (this.mPosition[1] * (float) super.getScreenHeight()), (int) (this.width * (float) super.getScreenWidth()), (int) (this.height * (float) super.getScreenHeight()));
    }
}
