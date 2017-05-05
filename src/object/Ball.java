package object;

import java.awt.Color;
import java.util.Random;

import game.MyJPanel;

public class Ball extends Item {
	
	private static float maxSpeed = 0.005f;

	public Ball(Random rand, MyJPanel jpanel) {
		super(new float[]{rand.nextFloat(), rand.nextFloat() / 3f},
						Ball.initSpeed(rand),
						new float[]{0f, 0f},
						0.01f,
						0.01f, jpanel);
		super.color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
	}
	
	/*public Ball(float[] mPosition, float[] mSpeed, float[] mAcceleration, MyJPanel jpanel) {
		super(mPosition.clone(), mSpeed.clone(), mAcceleration.clone(), 10, 10, jpanel);
	}*/
	
	public void bounding(){
		if(super.mPosition[0] <= 0) {
			super.mSpeed[0] = Math.abs(super.mSpeed[0]);
		}
		if(super.mPosition[1] <= 0) {
			super.mSpeed[1] = Math.abs(super.mSpeed[1]);
		}
		if(super.mPosition[0] + super.width >= 1f) {
			super.mSpeed[0] = -Math.abs(super.mSpeed[0]);
		}
		if(super.mPosition[1] >= 1f){
			super.mSpeed[1] = -Math.abs(super.mSpeed[1]);
		}
	}
	
	public static float[] initSpeed(Random rand){
		int directionId = rand.nextInt(4);
		if(directionId == 0) {
			double angle = rand.nextDouble() * Math.PI / 6 + Math.PI / 6;
			return new float[]{(float) Math.cos(angle) * Ball.maxSpeed, (float) Math.sin(angle) * Ball.maxSpeed};
		} else if(directionId == 1) {
			double angle = rand.nextDouble() * Math.PI / 6 + Math.PI * 2d / 3d;
			return new float[]{(float) Math.cos(angle) * Ball.maxSpeed, (float) Math.sin(angle) * Ball.maxSpeed};
		} else if(directionId == 2) {
			double angle = -rand.nextDouble() * Math.PI / 6 - Math.PI *2d / 3d;
			return new float[]{(float) Math.cos(angle) * Ball.maxSpeed, (float) Math.sin(angle) * Ball.maxSpeed};
		} else {
			double angle = -rand.nextDouble() * Math.PI / 6 - Math.PI / 6;
			return new float[]{(float) Math.cos(angle) * Ball.maxSpeed, (float) Math.sin(angle) * Ball.maxSpeed};
		}
	}
}
