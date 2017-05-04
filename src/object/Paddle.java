package object;

import game.MyJFrame;
import game.MyJPanel;

public class Paddle extends Item {

	public Paddle(int screenW, int screenH) {
		super(new float[]{screenW / 2, screenH - 50}, new float[]{0f, 0f}, new float[]{0f, 0f}, 50, 20, screenW, screenH);
	}
	
	public void setPos(int x) {
		super.mPosition[0] = x;
	}
	
	public void ballBouding(Ball b){
		if(b.mPosition[0] < this.mPosition[0] + this.width && b.mPosition[0] + b.width > this.mPosition[0] && b.mPosition[1] > this.mPosition[1] + this.height && b.mPosition[1] + b.height > this.mPosition[1]) {
			b.mPosition[1] = -Math.abs(b.mPosition[1]);
		}
	}
}
