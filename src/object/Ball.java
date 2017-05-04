package object;

import java.util.Random;

import game.MyJPanel;
import util.Vector;

public class Ball extends Item {

	public Ball(Random rand, MyJPanel jpanel) {
		super(new float[]{jpanel.getWidth() / 2, jpanel.getHeight() / 2},
						Vector.normalize2f(new float[]{rand.nextFloat(), rand.nextFloat()}),
						new float[]{0f, 0f},
						10,
						10, jpanel);
	}
	
	public Ball(float[] mPosition, float[] mSpeed, float[] mAcceleration, MyJPanel jpanel) {
		super(mPosition.clone(), mSpeed.clone(), mAcceleration.clone(), 10, 10, jpanel);
	}
	
	public void bounding(){
		if(super.mPosition[0] <= 0) {
			super.mSpeed[0] = Math.abs(super.mSpeed[0]);
		}
		if(super.mPosition[1] <= 0) {
			super.mSpeed[1] = Math.abs(super.mSpeed[1]);
		}
		if(super.mPosition[0] + super.width >= super.getScreenWidth()) {
			super.mSpeed[0] = -Math.abs(super.mSpeed[0]);
		}
		if(super.mPosition[1] >= super.getScreenHeight()){
			super.mSpeed[1] = -Math.abs(super.mSpeed[1]);
		}
	}
}
