package object;

import game.MyJPanel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import util.Vector;

public class Particule {

    private float[] mPosition;
    private float[] mSpeed;
    private MyJPanel jpanel;

    /**
     * Cree une particule a la position courante avec une vitesse aleatoire
     * 
     * @param mPosition
     *            La position
     * @param rand
     *            L'objet Random pour la generation de la vitesse
     * @param jpanel
     *            Le MyJPanel contenant le graphisme
     */
    public Particule(float[] mPosition, Random rand, MyJPanel jpanel) {
	float maxSpeed = rand.nextFloat() * 0.01f;
	this.mPosition = mPosition;
	double angle = rand.nextDouble() * Math.PI * 2d;
	this.mSpeed = new float[] { maxSpeed * (float) Math.cos(angle), maxSpeed * (float) Math.sin(
		angle) };
	this.jpanel = jpanel;
    }

    /**
     * Fait bouger la particule et decremente sa vitesse
     */
    public void move() {
	this.mPosition[0] += this.mSpeed[0];
	this.mPosition[1] += this.mSpeed[1];
	this.mSpeed[0] /= 1.1f;
	this.mSpeed[1] /= 1.1f;
    }

    /**
     * Retourne vrai si la particule a une vitesse suffisante
     * 
     * @return isAlive
     */
    public boolean isAlive() {
	return Vector.length2f(this.mSpeed) > 0.0001f;
    }

    /**
     * Dessine une particule
     * 
     * @param g2
     *            L'objet Graphics2D permettant de dessiner
     */
    public void draw(Graphics2D g2) {
	g2.setColor(Color.WHITE);
	g2.fillOval((int) (this.mPosition[0] * this.jpanel.getWidth()), (int) (this.mPosition[1]
		* this.jpanel.getHeight()), (int) (0.005f * this.jpanel.getWidth()), (int) (0.005f
			* this.jpanel.getHeight()));
    }

}
