package object;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import game.MyJPanel;

public class Item {

    public enum CollisionSide {
	LEFT, RIGHT, DOWN, UP, NONE
    }

    protected Random rand;
    private MyJPanel jpanel;

    protected float[] mPosition;
    protected float[] mSpeed;
    protected float[] mAcceleration;

    protected float width;
    protected float height;

    protected Rectangle2D rect;

    protected Color color;

    /**
     * Initialise une item de base pour notre jeu
     * 
     * @param mPosition
     *            La position
     * @param mSpeed
     *            La vitesse
     * @param mAcceleration
     *            L'acceleration
     * @param width
     *            La largeur de l'item
     * @param height
     *            La hauteur de l'item
     * @param jpanel
     *            Le MyJPanel contenant le graphisme
     */
    public Item(float[] mPosition, float[] mSpeed, float[] mAcceleration, float width, float height,
	    MyJPanel jpanel) {
	this.mPosition = mPosition.clone();
	this.mSpeed = mSpeed.clone();
	this.mAcceleration = mAcceleration.clone();
	this.width = width;
	this.height = height;
	this.rect = new Rectangle2D.Float(this.mPosition[0], this.mPosition[1], this.width,
		this.height);
	this.jpanel = jpanel;
	this.color = Color.RED;
	this.rand = new Random(System.currentTimeMillis());
    }

    /**
     * Detection de collision entre deux items
     * 
     * @param other
     *            L'item entrant ou non en collision
     * @return Vrai si il y a collision
     */
    public boolean intersect(Item other) {
	return this.rect.intersects(other.rect);
    }

    /**
     * Detecte le cote de collision d'un item avec un autre et modifie la
     * vitesse selon le cote de la collision
     * 
     * @param other
     *            L'item a faire rebondir
     */
    public void collide(Item other) {
	if (other.rect.intersectsLine(this.mPosition[0], this.mPosition[1], this.mPosition[0]
		+ this.width, this.mPosition[1])) {
	    other.mSpeed[1] = -Math.abs(other.mSpeed[1]);
	}
	if (other.rect.intersectsLine(this.mPosition[0], this.mPosition[1], this.mPosition[0],
		this.mPosition[1] + this.height)) {
	    other.mSpeed[0] = -Math.abs(other.mSpeed[0]);
	}
	if (other.rect.intersectsLine(this.mPosition[0] + this.width, this.mPosition[1],
		this.mPosition[0] + this.width, this.mPosition[1] + this.height)) {
	    other.mSpeed[0] = Math.abs(other.mSpeed[0]);
	}
	if (other.rect.intersectsLine(this.mPosition[0], this.mPosition[1] + this.height,
		this.mPosition[0] + this.width, this.mPosition[1] + this.height)) {
	    other.mSpeed[1] = Math.abs(other.mSpeed[1]);
	}
    }

    /**
     * Faire bouger l'objet selon son acceleration et sa vitesse
     * 
     * @param acceleration
     *            L'acceleration a affecter
     */
    public void move(float[] acceleration) {
	this.mAcceleration = acceleration.clone();
	this.mSpeed[0] += this.mAcceleration[0];
	this.mSpeed[1] += this.mAcceleration[1];

	this.mPosition[0] += this.mSpeed[0];
	this.mPosition[1] += this.mSpeed[1];

	this.rect = new Rectangle2D.Float(this.mPosition[0], this.mPosition[1], this.width,
		this.height);
    }

    /**
     * Retourne la largeur du MyJPanel
     * 
     * @return ScreenWidth
     */
    public int getScreenWidth() {
	return this.jpanel.getWidth();
    }

    /**
     * Retourne la hauteur du MyJPanel
     * 
     * @return ScreenHeight
     */
    public int getScreenHeight() {
	return this.jpanel.getHeight();
    }

    /**
     * Dessine un rectangle de couleur noir
     * 
     * @param g2
     *            L'objet Graphics2D permettant le dessin
     */
    public void draw(Graphics2D g2) {
	g2.fillRect((int) (this.mPosition[0] * (float) this.jpanel.getWidth()),
		(int) (this.mPosition[1] * (float) this.jpanel.getHeight()), (int) (this.width
			* (float) this.jpanel.getWidth()), (int) (this.height * (float) this.jpanel
				.getHeight()));
	g2.setColor(Color.BLACK);
	g2.drawRect((int) (this.mPosition[0] * (float) this.jpanel.getWidth()),
		(int) (this.mPosition[1] * (float) this.jpanel.getHeight()), (int) (this.width
			* (float) this.jpanel.getWidth()), (int) (this.height * (float) this.jpanel
				.getHeight()));
    }
}
