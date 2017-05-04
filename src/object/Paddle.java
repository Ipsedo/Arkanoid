package object;

import game.MyJFrame;
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
		super.mPosition[0] = x;
	}
	
	/**
	 * Pas Bon
	 * @param b La balle que l'on veut faire rebondir
	 */
	public void ballBouding(Ball b){
		if(b.mPosition[0] < this.mPosition[0] + this.width 
			&& b.mPosition[0] + b.width > this.mPosition[0] 
			&& b.mPosition[1] < this.mPosition[1] + this.height 
			&& b.mPosition[1] + b.height > this.mPosition[1]) {
				b.mSpeed[1] = -Math.abs(b.mSpeed[1]);
		}
	}
}
