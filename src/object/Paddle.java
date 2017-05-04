package object;

import game.MyJPanel;

public class Paddle extends Item {

	public Paddle() {
		super(new float[]{MyJPanel.WIDTH / 2, MyJPanel.HEIGHT - 50}, new float[]{0f, 0f}, new float[]{0f, 0f}, 50, 20);
	}
	
	public void setPos(int x) {
		super.mPosition[0] = x;
	}
}
