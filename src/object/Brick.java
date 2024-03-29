package object;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;
import java.util.Random;

import game.MyJPanel;

public class Brick extends Item {

    public static float width = 1f / 20f;
    public static float height = 1f / 50f;

    private int life;

    private int isBonus;

    /**
     * Initialise une brique avec la position, vitesse, acceleration, vie, bonus
     * et le MyJpanel donnes
     * 
     * @param mPosition
     *            La position
     * @param mSpeed
     *            La vitesse
     * @param mAcceleration
     *            L'acceleration
     * @param jpanel
     *            Le MyJpanel contenant le graphisme
     * @param life
     *            La vie de la brique
     * @param isBonus
     *            Le bonus de la brique (0 : pas de bonus, 1 : reduit la taille
     *            de la raquette, 2 : augmente la taille de la raquette, 3 :
     *            ajoute une balle)
     */
    public Brick(float[] mPosition, float[] mSpeed, float[] mAcceleration, MyJPanel jpanel,
	    int life, int isBonus) {
	super(mPosition, mSpeed, mAcceleration, width, height, jpanel);
	this.life = life;
	this.isBonus = isBonus;
    }

    /**
     * On surcharge cette methode pour ne pas retirer de la vie a l'item entrant
     * en collision avec la brique
     */
    public boolean intersect(Item other) {
	if (this.rect.intersects(other.rect)) {
	    this.life--;
	    return true;
	}
	return false;
    }

    /**
     * Retourne le score de la brique
     * 
     * @return Le score de la brique
     */
    public int getScore() {
	return (this.life + 1) * (this.life + 1) * 5;
    }

    /**
     * Retourne vrai si la brique est vivante
     * 
     * @return isAlive
     */
    public boolean isAlive() {
	return this.life > 0;
    }

    /**
     * Affecte le bonus soit a la raquette, soit a la liste de balles
     * 
     * @param paddle
     *            La raquette
     * @param balls
     *            La liste de balles
     * @param jpanel
     *            Le MyJPanel contenant le graphisme (pour la creation de balle)
     * @param rand
     *            Une instance de Random (pour la creation de balle)
     */
    public void makeBonus(Paddle paddle, List<Ball> balls, MyJPanel jpanel, Random rand) {
	if (this.isBonus == 2 && paddle.width < 0.6f) {
	    paddle.width += 0.02f;
	} else if (this.isBonus == 1 && paddle.width > 0.11f) {
	    paddle.width -= 0.02f;
	} else if (this.isBonus == 3) {
	    balls.add(new Ball(rand, jpanel));
	}
    }

    /**
     * Produit une explosion a la position de la brique
     * 
     * @param points
     *            La liste de particules
     * @param jpanel
     *            Le MyJpanel (utilise pour la creation de particules)
     */
    public void makeExplosion(List<Particule> points, MyJPanel jpanel) {
	float[] pos = new float[] { this.mPosition[0] + this.width / 2f, this.mPosition[1]
		+ this.height / 2f };
	Random rand = new Random(System.currentTimeMillis());
	for (int i = 0; i < 100; i++) {
	    points.add(new Particule(pos.clone(), rand, jpanel));
	}
    }

    /**
     * Dessine une brique selon son bonus
     */
    public void draw(Graphics2D g2) {
	if (this.life == 3) {
	    g2.setColor(new Color(192, 57, 43));
	} else if (this.life == 2) {
	    g2.setColor(new Color(211, 84, 0));
	} else if (this.life == 1) {
	    g2.setColor(new Color(243, 156, 18));
	}
	super.draw(g2);

	if (this.isBonus == 3) {
	    g2.setColor(Color.BLACK);
	    g2.fillOval((int) ((super.mPosition[0] + super.width / 2f - super.height / 2)
		    * (float) super.getScreenWidth()), (int) (super.mPosition[1]
			    * (float) super.getScreenHeight()), (int) (super.height
				    * (float) super.getScreenWidth()), (int) (super.height
					    * (float) super.getScreenHeight()));
	}
	// Bonus paddle size++
	else if (this.isBonus == 2) {
	    g2.setColor(new Color(46, 240, 113));
	    g2.fillRect((int) ((super.mPosition[0] + 9 * super.width / 20f)
		    * (float) super.getScreenWidth()), (int) ((super.mPosition[1] + super.height
			    / 10f) * (float) super.getScreenHeight()), (int) (super.width / 10
				    * (float) super.getScreenWidth()), (int) ((super.height
					    - 0.004f) * (float) super.getScreenHeight()));

	    g2.fillRect((int) ((super.mPosition[0] + 6 * super.width / 20)
		    * (float) super.getScreenWidth()), (int) ((super.mPosition[1] + 18
			    * super.height / 40f) * (float) super.getScreenHeight()), (int) ((2
				    * super.width / 5 * (float) super.getScreenWidth())),
		    (int) (super.height / 5 * (float) super.getScreenHeight()));
	}
	// Bonus paddle size--
	else if (this.isBonus == 1) {
	    g2.setColor(new Color(207, 0, 15));
	    g2.fillRect((int) ((super.mPosition[0] + 6 * super.width / 20)
		    * (float) super.getScreenWidth()), (int) ((super.mPosition[1] + 18
			    * super.height / 40f) * (float) super.getScreenHeight()), (int) (2
				    * super.width / 5 * (float) super.getScreenWidth()),
		    (int) (super.height / 5 * (float) super.getScreenHeight()));
	}
    }

    public String toString() {
	return super.mPosition[0] + " " + super.mPosition[1] + " " + this.life + " " + Integer
		.toString(this.isBonus);
    }
}
