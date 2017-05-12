package object;

import game.MyJPanel;

import java.awt.Color;
import java.awt.Graphics2D;

public class Score {

    private int currentScore;
    private MyJPanel jpanel;

    /**
     * Initialise le score
     * 
     * @param jpanel
     *            Le MyJPanel contenant le graphisme
     */
    public Score(MyJPanel jpanel) {
	this.currentScore = 0;
	this.jpanel = jpanel;
    }

    /**
     * Incremente le score
     * 
     * @param score
     *            La valeur a ajouter au score
     */
    public void incrScore(int score) {
	this.currentScore += score;
    }

    /**
     * Remet le score a zero
     */
    public void reset() {
	this.currentScore = 0;
    }

    /**
     * Divise le score par deux
     */
    public void divByTwo() {
	this.currentScore /= 2;
    }

    /**
     * Dessine le score courant
     * 
     * @param g2
     *            L'objet Graphics2D permettant le dessin
     */
    public void draw(Graphics2D g2) {
	g2.setColor(new Color(44, 62, 80));
	g2.drawChars(("Score : " + this.currentScore).toCharArray(), 0, ("Score : "
		+ this.currentScore).toCharArray().length, (int) (0.5 * this.jpanel.getWidth()),
		(int) (0.8 * this.jpanel.getHeight()));
    }

}
