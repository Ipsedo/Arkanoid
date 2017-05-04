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
}
