package object;

import java.awt.Color;

import game.MyJPanel;

public class Paddle extends Item {
	
	/**
	 * 
	 * @param jpanel
	 */
	public Paddle(MyJPanel jpanel) {
		super(new float[]{jpanel.getWidth() / 2, jpanel.getHeight() - 50}, new float[]{0f, 0f}, new float[]{0f, 0f}, 50, 20, jpanel);
	}
	
	/**
	 * Definir la nouvelle position de la raquette
	 * @param x Nouvelle position en largeur de la raquette
	 */
	public void setPos(int x) {
		super.mPosition[0] = x - super.width / 2;
	}
	
	/**
	 * Pas Bon
	 * @param b La balle que l'on veut faire rebondir
	 */
	public void ballBouding(Ball b){
		if(super.intersect(b)) {
				b.mSpeed[1] = -Math.abs(b.mSpeed[1]);
				this.color = new Color(super.rand.nextInt(255), super.rand.nextInt(255), super.rand.nextInt(255));
		}
	}
}
