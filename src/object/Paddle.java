package object;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

import game.MyJPanel;

public class Paddle extends Item {

    private final static float initialHeight = 0.02f;
    
    /**
     * 
     * @param jpanel Le MyJPanel contenant la raquette
     */
    public Paddle(MyJPanel jpanel) {
	super(new float[] { 0.5f, 1f - initialHeight }, new float[] { 0f, 0f }, new float[] { 0f, 0f }, 0.2f, initialHeight, jpanel);
    }

    /**
     * 
     * @param x La nouvelle position en pixel de la souris
     */
    public void setPos(int x) {
	super.mPosition[0] = (float) x / super.getScreenWidth() - super.width / 2f;
	super.mPosition[1] = 1f - initialHeight;
    }
    
    @Override
    public void collide(Item other) {
	if(other.rect.intersectsLine(this.mPosition[0], this.mPosition[1], this.mPosition[0] + this.width, this.mPosition[1])) {
	    other.mSpeed[1] = -Math.abs(other.mSpeed[1]);
	} else if(other.rect.intersectsLine(this.mPosition[0], this.mPosition[1], this.mPosition[0], this.mPosition[1] + this.height)) {
	    other.mSpeed[0] = -Math.abs(other.mSpeed[0]);
	} else if(other.rect.intersectsLine(this.mPosition[0] + this.width, this.mPosition[1], this.mPosition[0] + this.width, this.mPosition[1] + this.height)) {
	    other.mSpeed[0] = Math.abs(other.mSpeed[0]);
	}
    }
}
