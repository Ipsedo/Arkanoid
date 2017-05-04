package object;

import java.util.Random;

import game.MyJPanel;
import util.Vector;

public class Ball extends Item {

	public Ball(Random rand, MyJPanel jpanel) {
		super(new float[]{jpanel.getWidth() / 3 + rand.nextFloat() * jpanel.getWidth() / 3, jpanel.getHeight() / 3 + rand.nextFloat() * jpanel.getHeight() / 3},
						Ball.initSpeed(rand),
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
	
	public static float[] initSpeed(Random rand){
		int directionId = rand.nextInt(4);
		if(directionId == 0) {
			double angle = rand.nextDouble() * Math.PI / 6 + Math.PI / 6;
			return new float[]{(float) Math.cos(angle), (float) Math.sin(angle)};
		} else if(directionId == 1) {
			double angle = rand.nextDouble() * Math.PI / 6 + Math.PI * 2d / 3d;
			return new float[]{(float) Math.cos(angle), (float) Math.sin(angle)};
		} else if(directionId == 2) {
			double angle = -rand.nextDouble() * Math.PI / 6 - Math.PI *2d / 3d;
			return new float[]{(float) Math.cos(angle), (float) Math.sin(angle)};
		} else {
			double angle = -rand.nextDouble() * Math.PI / 6 - Math.PI / 6;
			return new float[]{(float) Math.cos(angle), (float) Math.sin(angle)};
		}
	}
}
