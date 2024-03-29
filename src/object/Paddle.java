package object;

import java.awt.Color;
import java.awt.Graphics2D;

import game.MyJPanel;
import util.Vector;

public class Paddle extends Item {

    private final static float initialHeight = 0.03f;

    /**
     * Initialise une raquette
     * 
     * @param jpanel
     *            Le MyJPanel contenant le graphisme
     */
    public Paddle(MyJPanel jpanel) {
	super(new float[] { 0.5f, 1f - initialHeight }, new float[] { 0f, 0f }, new float[] { 0f,
		0f }, 0.15f, initialHeight - 0.015f, jpanel);
    }

    /**
     * Modifie la position de la raquette
     * 
     * @param x
     *            la nouvelle position de la raquette en pixels
     */
    public void setPos(int x) {
	super.mPosition[0] = (float) x / super.getScreenWidth() - super.width / 2f;
	super.mPosition[1] = 1f - initialHeight;
    }

    /**
     * Reinitialise la largeur de la raquette
     */
    public void resetPaddle() {
	super.width = 0.15f;
    }

    /**
     * On surcharge la methode pour modifier le rebond en fonction de la
     * position (utile pour le rebond de la balle)
     */
    @Override
    public void collide(Item other) {
	if (other.rect.intersectsLine(this.mPosition[0], this.mPosition[1], this.mPosition[0]
		+ this.width, this.mPosition[1])) {
	    float dx = this.mPosition[0] + this.width * 0.5f - (other.mPosition[0] + other.width
		    * 0.5f);
	    dx = dx / (this.width * 0.5f);
	    float length = Vector.length2f(other.mSpeed);
	    double angle = dx * Math.PI * 0.4d;
	    other.mSpeed[0] = -length * (float) Math.sin(angle);
	    other.mSpeed[1] = -length * (float) Math.cos(angle);
	    // other.mSpeed[1] = -Math.abs(other.mSpeed[1]);
	} else if (other.rect.intersectsLine(this.mPosition[0], this.mPosition[1],
		this.mPosition[0], this.mPosition[1] + this.height)) {
	    other.mSpeed[0] = -Math.abs(other.mSpeed[0]);
	} else if (other.rect.intersectsLine(this.mPosition[0] + this.width, this.mPosition[1],
		this.mPosition[0] + this.width, this.mPosition[1] + this.height)) {
	    other.mSpeed[0] = Math.abs(other.mSpeed[0]);
	}
    }

    /**
     * On dessine la raquette
     */
    public void draw(Graphics2D g2) {
	g2.setColor(Color.RED);
	g2.fillRect((int) (super.mPosition[0] * (float) super.getScreenWidth()),
		(int) (super.mPosition[1] * (float) super.getScreenHeight()), (int) (super.width
			* (float) super.getScreenWidth()), (int) (super.height
				* (float) super.getScreenHeight()));
	g2.setColor(Color.BLACK);
	g2.drawRect((int) (super.mPosition[0] * (float) super.getScreenWidth()),
		(int) (super.mPosition[1] * (float) super.getScreenHeight()), (int) (super.width
			* (float) super.getScreenWidth()), (int) (super.height
				* (float) super.getScreenHeight()));
    }
}
