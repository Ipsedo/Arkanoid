package object;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

import game.MyJPanel;

public class Paddle extends Item {
	
	/**
	 * 
	 * @param jpanel
	 */
	public Paddle(MyJPanel jpanel) {
		super(new float[]{0.5f , 0.98f}, new float[]{0f, 0f}, new float[]{0f, 0f}, 0.2f, 0.01f, jpanel);
	}
	
	/**
	 * Definir la nouvelle position de la raquette
	 * @param x Nouvelle position en largeur de la raquette (en pixel)
	 */
	public void setPos(int x) {
		super.mPosition[0] = (float) x / super.getScreenWidth() - super.width / 2f;
		super.mPosition[1] = 0.98f;
	}
	
	/**
	 * Pas Bon
	 * @param b La balle que l'on veut faire rebondir
	 */
	public void ballBouding(Ball b){
		if(super.intersect(b)) {
				b.mSpeed[1] = -Math.abs(b.mSpeed[1]);
				this.color = new Color(super.rand.nextInt(255), super.rand.nextInt(255), super.rand.nextInt(255));
				b.color = new Color(super.rand.nextInt(255), super.rand.nextInt(255), super.rand.nextInt(255));
		}
	}
}
